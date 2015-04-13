package cl.uchile.coupling.messaging;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.Executor;

import cl.uchile.cos.persistance.StateManager;
import cl.uchile.cos.session.Client;
import cl.uchile.cos.session.SessionManager;
import cl.uchile.cos.sync.Message;
import cl.uchile.cos.sync.adapters.ServerAdapter;
import cl.uchile.coupling.persistance.PersistentMessageQueue;
import cl.uchile.coupling.resources.MessageFactory;
import cl.uchile.coupling.resources.ResourceMonitor;

public class JSONBufferAdapter extends ServerAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746589582608046977L;
	private StringBuffer buffer;
	private String id;
	private int INIT_BUFFER_SIZE = 1024;
	private int messagesBlockSize = 10;
	private MessageFactory messageFactory;
	private ResourceMonitor resourceMonitor;
	private PersistentMessageQueue queue;
	private Boolean enqueuedMesages = false;
	private transient Executor executor;
	private int objectToLoad = 0;

	public JSONBufferAdapter(Client client, String sessionId,
			SessionManager manager, MessageFactory messageFactory,
			Boolean echo, ResourceMonitor resourceMonitor,
			PersistentMessageQueue queue, Executor executor,
			Integer messagesBlockSize) {
		super(sessionId, manager, echo, client);

		this.buffer = new StringBuffer(INIT_BUFFER_SIZE);
		this.setMessageFactory(messageFactory);
		this.resourceMonitor = resourceMonitor;
		this.queue = queue;
		this.executor = executor;
		this.messagesBlockSize = messagesBlockSize;

	}

	public JSONBufferAdapter() {
		super();
		this.buffer = new StringBuffer(INIT_BUFFER_SIZE);
	}

	@Override
	public synchronized void sendToClient(Message... event) {
		this.sendToClientWithPriority(2,event);

	}

	public synchronized void sendToClientWithPriority(
			Integer priority,Message... event) {
		if (this.sendToDisk())
			this.enqueueMesage(priority,event);
		else
			this.bufferMessage(event);

	}

	private void bufferMessage(Message... events) {

		Writer out = new StringWriter();
		for (Message event : events) {
			try {
				if (buffer.length() == 0) {
					out.append("{\"messages\":[");
					messageFactory.toJSON(event, out);
				} else {
					out.append(" ,");
					messageFactory.toJSON(event, out);
				}
				out.flush();
				buffer.append(out.toString());
			} catch (IOException e) {
				// TODO: logging
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO: logging
					e.printStackTrace();
				}
			}
		}

	}

	private synchronized void enqueueMesage( Integer priority,Message... event) {
		this.queue.enqueue(getClient(), getSessionId(), priority, event);
		this.enqueuedMesages = true;
	}

	private synchronized void dequeueMessages() {
		if (!this.enqueuedMesages)
			return;
		while (this.buffer.length() <= this.resourceMonitor.getBytesPerUser()) {
			List<String> messages = this.queue.dequeue(getClient(),
					getSessionId(), messagesBlockSize);
			for (String json : messages)
				this.bufferMessage(messageFactory.createFromJSON(json));
			if (messages.size() < messagesBlockSize) {
				this.enqueuedMesages = false;
				break;
			}
		}
	}

	public synchronized void getMessages(Writer writer) {

		if (this.buffer.length() > 0) {
			this.buffer.append("]}");
			try {
				writer.write(this.buffer.toString());
			} catch (IOException e) {
				// TODO LOG
				e.printStackTrace();
			}
			this.buffer = new StringBuffer(INIT_BUFFER_SIZE);
			dequeueMessages();
		}
	}

	public synchronized String getMessages() {

		if (this.buffer.length() > 0 && getObjectToLoad() == 0) {
			this.buffer.append("]}");

			String result = buffer.toString();

			this.buffer = new StringBuffer(INIT_BUFFER_SIZE);
			dequeueMessages();
			return result;
		}
		return "";
	}

	@Override
	public void setSessionId(String sessionId) {
		super.setSessionId(sessionId);
		if (getClient() != null) {
			this.id = getClient().getId() + "-" + getSessionId();
			this.queue.clear(getClient(), getSessionId());
		}

	}

	public void setClient(Client client) {
		super.setClient(client);
		if (getSessionId() != null) {
			this.id = getClient().getId() + "-" + getSessionId();
			this.queue.clear(getClient(), getSessionId());
		}

	}

	public boolean equals(JSONBufferAdapter adapter) {
		return adapter.getClient().equals(this.getClient())
				&& adapter.getSessionId() == this.getSessionId();
	}

	public void sendToServer(CharSequence json) {
		this.sendToServer(messageFactory.createFromJSON(json));
	}

	@Override
	public boolean ready() {
		return this.buffer.length() == 0;
	}

	public MessageFactory getMessageFactory() {
		return messageFactory;
	}

	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}

	public int getMessagesBlockSize() {
		return messagesBlockSize;
	}

	public void setMessagesBlockSize(int messagesBlockSize) {
		this.messagesBlockSize = messagesBlockSize;
	}

	public ResourceMonitor getResourceMonitor() {
		return resourceMonitor;
	}

	public void setResourceMonitor(ResourceMonitor resourceMonitor) {
		if (this.resourceMonitor != null)
			this.resourceMonitor.removeUser();
		this.resourceMonitor = resourceMonitor;
		this.resourceMonitor.addUser();
	}

	public PersistentMessageQueue getQueue() {
		return queue;
	}

	public void setQueue(PersistentMessageQueue queue) {
		this.queue = queue;
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public String toString() {
		return this.id;
	}

	@Override
	public int hashCode() {
		if (this.id == null)
			return super.hashCode();
		return this.id.hashCode();
	}

	private boolean sendToDisk() {
		return this.enqueuedMesages
				|| this.buffer.length() >= this.resourceMonitor
						.getBytesPerUser();
	}

	@Override
	public synchronized void initializeObjectData(String objectId,
			StateManager stateManagere) {
		this.objectToLoad++;
		long timestamp=System.currentTimeMillis();
		for(int page=1;;++page){
			if(this.sendToDisk()){
				this.executor.execute(new JSONBufferAdapterLoaderTask(this, stateManagere, timestamp, this.messagesBlockSize, page, objectId));
				return;
			}
			List<Message> messages=stateManagere.getPersistedState(objectId, page, this.messagesBlockSize, timestamp);
			this.sendToClientWithPriority(1, messages.toArray(new Message[messages.size()]));
			if(messages.size()!=this.messagesBlockSize){
				this.objectToLoad--;
				return;
			}
			
			
		}

	}

	public synchronized int getObjectToLoad() {
		return objectToLoad;
	}

	public synchronized void setObjectToLoad(int objectToLoad) {
		this.objectToLoad = objectToLoad;
		
	}

	@Override
	public void destroy() {
		if (this.resourceMonitor != null)
			this.resourceMonitor.removeUser();
		this.resourceMonitor = null;
		if (this.queue != null)
			this.queue.clear(getClient(), getSessionId());
		this.queue = null;
	}

	
}
