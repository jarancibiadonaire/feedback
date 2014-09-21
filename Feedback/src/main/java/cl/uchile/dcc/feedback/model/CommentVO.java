package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable {

	private static final long serialVersionUID = -463067979140911659L;
	
	private Integer id;
	
	private String comment;
	
	private UserVO user;
		
	private Date createdDate;
	
	private CommentVO responseTo;
	
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

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public CommentVO getResponseTo() {
		return responseTo;
	}

	public void setResponseTo(CommentVO responseTo) {
		this.responseTo = responseTo;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
