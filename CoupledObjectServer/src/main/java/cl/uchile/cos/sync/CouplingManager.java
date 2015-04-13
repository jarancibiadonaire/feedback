package cl.uchile.cos.sync;

import java.io.Serializable;
import java.util.List;

import cl.uchile.cos.persistance.StateManager;
import cl.uchile.cos.sync.adapters.ServerAdapter;
import cl.uchile.cos.sync.listeners.Listener;

public interface CouplingManager extends Serializable {

	public void addListener(Listener listener);

	public void removeListener(Listener listener);

	public boolean registerMessage(Message message, ServerAdapter adapter);

	public boolean registerMessage(Message message, ServerAdapter adapter,
			boolean echo);

	public void couple(String objectId, ServerAdapter adapter);

	public void couple(String objectId, ServerAdapter adapter,
			List<Message> messages);

	public void decouple(ServerAdapter adapter);

	public void decouple(String objectId, ServerAdapter adapter);

	public void setStateManager(StateManager stateManager);

	public String getSessionId();

	public void setSessionId(String sessionId);

	public void destroy();

	void couple(String objectId, ServerAdapter adapter, List<Message> messages,
			Boolean initMessage);

	void couple(String objectId, ServerAdapter adapter, Boolean initMessage);

}
