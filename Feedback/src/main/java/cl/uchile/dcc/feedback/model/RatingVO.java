package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.Date;

public class RatingVO implements Serializable {

	private static final long serialVersionUID = -7340954027890106697L;
	
	private Integer id;
	
	private String user;
	
	private Integer score;
	
	private Date createdDate;
	
	private Integer feed;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getFeed() {
		return feed;
	}

	public void setFeed(Integer feed) {
		this.feed = feed;
	}

}
