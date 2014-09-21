package cl.uchile.dcc.feedback.cassandra.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="tag_session")
public class TagSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "tag_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private Integer tagId;
	
	@PrimaryKeyColumn(name = "session_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private Integer sessionId;
	
	@PrimaryKeyColumn(name = "time", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private Date time;
	
	@Column(value="feed_id")
	private Integer feedId;
	
	
	 public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer locationId) {
		this.tagId = locationId;
	}

	public Integer getFeedId() {
		return feedId;
	}

	public void setFeedId(Integer feedId) {
		this.feedId = feedId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
