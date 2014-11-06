package cl.uchile.dcc.feedback.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
@Indexed
@Entity
@Table(name="comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 6708689733686666165L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private Integer id;
	
	@IndexedEmbedded
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="comment")
	private String comment;
	
	@JoinColumn(name="user_id")
	@ManyToOne	
	private User user;
	
	@JoinColumn(name="feed")
	@OneToOne
	private Feed feed;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@JoinColumn(name="response_to")
	@OneToOne
	private Comment responseTo;
	
	@Column(name="comment_level")
	private Integer level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Comment getResponseTo() {
		return responseTo;
	}

	public void setResponseTo(Comment responseTo) {
		this.responseTo = responseTo;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
