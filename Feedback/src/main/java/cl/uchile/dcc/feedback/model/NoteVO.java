package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.Date;

public class NoteVO implements Serializable {
	
	private static final long serialVersionUID = -2129720463533638397L;

	private Integer id;

	private String note;

	private UserVO user;

	private String visibility;
	
	private Date createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
