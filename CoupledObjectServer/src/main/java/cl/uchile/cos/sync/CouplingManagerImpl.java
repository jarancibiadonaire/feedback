package cl.uchile.cos.sync;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

import cl.uchile.cos.persistance.StateManager;
import cl.uchile.cos.sync.adapters.ServerAdapter;
import cl.uchile.cos.sync.listeners.CouplingListener;
import cl.uchile.cos.sync.listeners.Listener;
import cl.uchile.cos.sync.listeners.MessageListener;

public class CouplingManagerImpl implements CouplingManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6184655961711874295L;

	final class MessagePropagator implements Runnable {
		private Message message;
		private ServerAdapter adapter;
		private boolean echo;

		public MessagePropagator(Message message, ServerAdapter adapter,
				boolean echo) {
			this.message = message;
			this.adapter = adapter;
			this.echo = echo;
		}

		@Override
		public void run() {
			try {
				for (MessageListener listener : messageListeners) {
					listener.onRecive(message, adapter);
				}
				propagate(message, adapter, echo);
			} catch (Exception e) {
				// TODO Logging
				// Muy importante manejar cualquier error que pueda suceder aca
				// pues si el error se lanza el temporizador se detiene
				e.printStackTrace();
			}
		}

	}

	private Map<String, Set<ServerAdapter>> adapters;
	private Set<MessageListener> messageListeners;
	private Set<CouplingListener> couplingListener;
	private StateManager stateManager;
	private Long messagesToGo = 0L;
	private transient Executor executor;
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
		this.stateManager.setSessionId(sessionId);
	}

	public CouplingManagerImpl(Executor executor) {
		this.adapters = new LinkedHashMap<String, Set<ServerAdapter>>();
		this.messageListeners = new LinkedHashSet<MessageListener>();
		this.couplingListener = new LinkedHashSet<CouplingListener>();
		this.executor = executor;
	}

	@Override
	public synchronized void addListener(Listener listener) {
		if (MessageListener.class.isInstance(listener))
			this.messageListeners.add((MessageListener) listener);
		else if (CouplingListener.class.isInstance(listener)) {
			this.couplingListener.add((CouplingListener) listener);
		}
	}

	@Override
	public synchronized void removeListener(Listener listener) {
		if (MessageListener.class.isInstance(listener))
			this.messageListeners.remove((MessageListener) listener);
		else if (CouplingListener.class.isInstance(listener)) {
			this.couplingListener.remove((CouplingListener) listener);
		}
	}

	private synchronized void propagate(Message message, ServerAdapter adapter,
			boolean echo) {
		String id = message.getObjectId();
		messagesToGo--;
		if (this.stateManager != null)
			this.stateManager.processMessage(message);
		for (ServerAdapter destAdapter : adapters.get(id)) {
			if (destAdapter.equals(adapter) && !echo)
				continue;
			destAdapter.sendToClient(message);
			for (MessageListener listener : this.messageListeners) {
				listener.onSendToAdapter(message, adapter);
			}
		}
		for (MessageListener listener : this.messageListeners) {
			listener.onPropagationEnd(message);
		}
	}

	/**
	 * Regista el mensaje para ser propadgado. Si el mensaje no es seguro por
	 * temas de sincronizacion retornara false y lo reenviara sincronizado al
	 * cliente en el futuro. De lo contrario no se lo enviara denuevo al cliente
	 * que lo envio.
	 * 
	 * @param message
	 * @param adapter
	 * @return true si el cliente puede ejecutar el evento, false si no.
	 */
	@Override
	public boolean registerMessage(Message message, ServerAdapter adapter) {
		return this.registerMessage(message, adapter, false);

	}

	@Override
	public boolean registerMessage(Message message, ServerAdapter adapter,
			boolean echo) {
		if (this.stateManager != null
				&& !this.stateManager.validateMessage(message, adapter)) {
			return false;
		}
		boolean safe = false;
		synchronized (this) {
			safe = isMessageSyncSafe(message, adapter);
			this.messagesToGo++;
		}

		message.setTimestamp(System.currentTimeMillis());
		try {
			// This prevent 2 messages to have the same timestamp at the small
			// (posible) cost of 1ms per request.
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO logging
			e.printStackTrace();
		}
		this.executor.execute(new MessagePropagator(message, adapter, !safe
				|| echo));
		return safe;
	}

	/**
	 * Revisa si el mensaje puede o no causar problemas de sincronizacion con el
	 * cliente. Esto significa revisar si hay algun mensaje emitido antes que
	 * aun no ha sido entregado a al cliente que envio este mensaje.
	 * 
	 * @param message
	 * @return
	 */
	private boolean isMessageSyncSafe(Message message, ServerAdapter adapter) {
		if (adapter.ready()) {
			if (this.messagesToGo == 0)
				return true;
		}
		return false;
	}

	@Override
	public synchronized void couple(String objectId, ServerAdapter adapter) {
		this.couple(objectId, adapter, null,null);
	}
	@Override
	public synchronized void couple(String objectId, ServerAdapter adapter,Boolean initMessage) {
		this.couple(objectId, adapter, null,initMessage);
	}

	@Override
	public synchronized void decouple(String objectId, ServerAdapter adapter) {
		if (this.adapters.containsKey(objectId)) {
			this.adapters.get(objectId).remove(adapter);
		}
		for (CouplingListener listener : this.couplingListener) {
			listener.onDecouple(objectId, adapter);
		}
	}

	@Override
	public synchronized void decouple(ServerAdapter adapter) {
		for (String objectId : this.adapters.keySet()) {
			this.decouple(objectId, adapter);
		}
	}

	@Override
	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	@Override
	public synchronized void couple(String objectId, ServerAdapter adapter,
			List<Message> messages) {
		this.couple(objectId, adapter, messages, null);
	}	

	@Override
	public synchronized void couple(String objectId, ServerAdapter adapter,
			List<Message> messages,Boolean initMessage) {
		if (this.stateManager != null && (initMessage==null || initMessage)) {
			adapter.initializeObjectData(objectId, stateManager);
		}
		if (!this.adapters.containsKey(objectId)) {
			this.adapters.put(objectId, new LinkedHashSet<ServerAdapter>());
		}
		this.adapters.get(objectId).add(adapter);
		for (CouplingListener listener : this.couplingListener) {
			listener.onCouple(objectId, adapter);
		}
		if (stateManager.getPersistedStateMessageCount(objectId) == 0 && messages != null) {
			for (Message message : messages) {
				this.registerMessage(message, adapter, true);
			}
		}

	}

	@Override
	public void destroy() {
		if (this.stateManager != null)
			this.stateManager.destroy();

	}

	static public void main(String[] args) {
		for (int i = 0; i < 100; ++i) {
			System.out.println(System.currentTimeMillis());
		}
	}
}
