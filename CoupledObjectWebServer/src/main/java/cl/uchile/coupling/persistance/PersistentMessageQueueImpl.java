package cl.uchile.coupling.persistance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.uchile.cos.session.Client;
import cl.uchile.cos.sync.Message;

@Repository("queue")
public class PersistentMessageQueueImpl implements PersistentMessageQueue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 478363087956132070L;
	@PersistenceContext
	private transient EntityManager em;

	@Override
	@Transactional
	public void enqueue(Client cliente, String sessionId, Integer priority,
			Message... messages) {
		for (Message message : messages) {
			this.em.persist(new QueuedMessage(cliente.getId(), sessionId,
					message.toString(), new Date().getTime(), priority));
		}

	}

	@Override
	@Transactional
	public List<String> dequeue(Client cliente, String sessionId, Integer max) {
		List<QueuedMessage> results = em
				.createQuery(
						"from QueuedMessage q where q.client=:client and q.session=:session order by q.priority asc , q.time asc",
						QueuedMessage.class).setFirstResult(0)
				.setMaxResults(max).setParameter("client", cliente.getId())
				.setParameter("session", sessionId).getResultList();
		List<String> messages = new ArrayList<String>(max);
		for (QueuedMessage q : results) {
			messages.add(q.getContent());
			em.remove(q);
		}
		return messages;
	}

	@Override
	@Transactional
	public void clear(Client client, String sessionId) {
		em.createQuery(
				"DELETE FROM QueuedMessage q WHERE q.client=:client and q.session=:session")
				.setParameter("client", client.getId())
				.setParameter("session", sessionId).executeUpdate();

	}

}
