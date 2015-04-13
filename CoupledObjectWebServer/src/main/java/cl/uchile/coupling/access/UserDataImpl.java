package cl.uchile.coupling.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cl.uchile.cos.session.SessionManager;
import cl.uchile.cos.util.IdGenerator;

public class UserDataImpl implements Serializable, UserData {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8526728181648156653L;
	private String sessionClient = null;
	private List<String> clients = new ArrayList<String>();
	private String userId;
	private SessionManager sessionManager;

	public UserDataImpl(IdGenerator<?> generator) {
		this.userId = generator.nextId().toString();
	}

	public List<String> getClients() {
		return clients;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public String getSessionClient() {
		return sessionClient;
	}

	public void setSessionClient(String sessionClient) {
		this.sessionClient = sessionClient;
	}

	public String getUserId() {
		return userId;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	public void destroy() {
		System.out.println("DESTROYING SESSION");
		for (String clientId : this.clients) {
			this.sessionManager.destroyClient(this.sessionManager
					.getClientById(Long.parseLong(clientId)));
		}

	}

}
