package cl.uchile.coupling.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.uchile.cos.persistance.AbstractStateManager;
import cl.uchile.cos.sync.Message;
import cl.uchile.coupling.resources.MessageFactory;

@Repository("stateManager")
@Scope(value = "prototype")
public class JPAEventStateManager extends AbstractStateManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4686818331424921805L;
	@PersistenceContext
	private transient EntityManager em;
	@Autowired
	private MessageFactory messageFactory;

	@Override
	@Transactional
	public void destroy() {
		Query q = em
				.createQuery("DELETE FROM Entry e WHERE e.session=:session");
		q.setParameter("session", getSessionId());
		q.executeUpdate();

	}

	@Override
	@Transactional
	public void processMessage(Message message) {
		String objectId = message.getObjectId();
		Entry entry = null;
		switch (message.getMessageType()) {

		case EVENT:
			entry = new Entry(this.getSessionId(), Entry.EVENT,
					message.toString(), objectId);
			break;
		case STATE:
			Query q = em
					.createQuery("DELETE FROM Entry e WHERE e.session=:session AND e.objectId=:objectId");
			q.setParameter("session", getSessionId());
			q.setParameter("objectId", objectId);
			q.executeUpdate();
			entry = new Entry(this.getSessionId(), Entry.STATE,
					message.toString(), objectId);

			break;

		}
		entry.setTime(message.getTimestamp());
		em.persist(entry);

	}

	@Override
	@Transactional
	public List<Message> getPersistedState(String objectId) {
		return this.getPersistedState(objectId, null, null,System.currentTimeMillis());
	}

	@Override
	public List<Message> getPersistedState(String objectId, Integer page,
			Integer pageSize,Long maxTime) {
		TypedQuery<Entry> q = em
				.createQuery(
						"FROM Entry e WHERE e.session=:session AND e.objectId=:objectId AND e.time<=:maxTime  ORDER BY e.type asc , e.time asc",
						Entry.class);
		q.setParameter("session", getSessionId());
		q.setParameter("objectId", objectId);
		q.setParameter("maxTime", maxTime);
		
		if (page != null)
			q.setFirstResult((page - 1) * pageSize);
		if (pageSize != null)
			q.setMaxResults(pageSize);
		
		List<Entry> result = q.getResultList();
		List<Message> messages = new ArrayList<Message>(result.size());
		for (Entry e : result) {
			String type = (e.getType() == Entry.EVENT) ? "EVENT" : "STATE";
			messages.add(messageFactory.createFromJSON(e.getObjectId(), type,
					e.getMessage()));
		}
		return messages;
	}

	@Override
	public Long getPersistedStateMessageCount(String objectId) {
		TypedQuery<Long> q = em
				.createQuery("SELECT count(*) FROM Entry e WHERE e.session=:session AND e.objectId=:objectId",Long.class);
		q.setParameter("session", getSessionId());
		q.setParameter("objectId", objectId);
		return q.getSingleResult();
	}

}
