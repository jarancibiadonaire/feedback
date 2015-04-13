package cl.uchile.coupling.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class QueuedMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	@Column(nullable=false)
	private Long client;
	@Column(nullable=false)
	private String content;
	@Column(nullable=false)
	private Long time;
	@Column(nullable=false)
	private String session;
	@Column(nullable=false)
	private Integer priority;

	public QueuedMessage() {
	}

	public QueuedMessage(Long client,String session, String content, Long time,Integer priority) {
		super();
		this.client = client;
		this.content = content;
		this.time = time;
		this.setSession(session);
		this.priority=priority;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
