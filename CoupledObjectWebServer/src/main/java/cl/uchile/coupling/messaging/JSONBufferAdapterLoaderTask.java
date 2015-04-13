package cl.uchile.coupling.messaging;

import java.util.List;

import cl.uchile.cos.persistance.StateManager;
import cl.uchile.cos.sync.Message;

public class JSONBufferAdapterLoaderTask implements Runnable {
	private JSONBufferAdapter adapter;
	private StateManager stateManager;
	static final private Object mutex = new Object();
	private Long timestamp;
	private Integer pageSize;
	private Integer page = 1;
	private String objectId;

	public JSONBufferAdapterLoaderTask(JSONBufferAdapter adapter,
			StateManager stateManager, Long timestamp, Integer pageSize,
			Integer initPage, String objectId) {
		super();
		this.adapter = adapter;
		this.stateManager = stateManager;
		this.timestamp = timestamp;
		this.pageSize = pageSize;
		this.page = initPage;
		this.objectId = objectId;
	}

	private void load() {
		synchronized (mutex) {

			for (;;this.page++) {
				List<Message> messages = this.stateManager.getPersistedState(
						objectId, page, pageSize, timestamp);
				this.adapter.sendToClientWithPriority(1,messages.toArray(new Message[messages.size()]));
				if (messages.size() < pageSize)
					break;
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Logging
					e.printStackTrace();
				}
			}
			this.adapter.setObjectToLoad(this.adapter.getObjectToLoad()-1);
		}

	}

	@Override
	public void run() {
		this.load();
	}

}
