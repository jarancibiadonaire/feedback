package cl.uchile.cos.session;

import java.io.Serializable;

import cl.uchile.cos.sync.adapters.ServerAdapter;

public interface Client extends Serializable{

	public void setId(Long id);
 
	public Long getId();

	public void addAdapter(String SessionId, ServerAdapter adapter);

	public ServerAdapter getAdapter(String sessionId);
	public boolean equals(Client c);
	public void destroy(); 

}
