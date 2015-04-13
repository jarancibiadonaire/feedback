package cl.uchile.coupling.persistance;

import java.io.Serializable;
import java.util.List;

import cl.uchile.cos.session.Client;
import cl.uchile.cos.sync.Message;


public interface PersistentMessageQueue extends Serializable{
	
	public void enqueue(Client cliente,String sessionId,Integer priority, Message... message) ;
	public List<String> dequeue(Client cliente,String sessionId, Integer max);
	public void clear(Client client, String sessionId);

}
