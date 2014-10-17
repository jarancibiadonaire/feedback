package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable {

	private static final long serialVersionUID = -463067979140911659L;
	
	private Integer id;
	
	private String comment;
	
	private String user;
		
	private Date createdDate;
	
	private String responseTo;
	
	private Integer level;
	
	private Integer feed;

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getResponseTo() {
		return responseTo;
	}

	public void setResponseTo(String responseTo) {
		this.responseTo = responseTo;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getFeed() {
		return feed;
	}

	public void setFeed(Integer feed) {
		this.feed = feed;
	}

}
