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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_tag")
public class UserTag implements Serializable {

	private static final long serialVersionUID = -7245262149852608107L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_tag_id")
	private Integer userTagId;
	
	@JoinColumn(name="tag")
	@ManyToOne
	private Tag tag;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="follow_date")
	private Date followDate;

	public Integer getUserTagId() {
		return userTagId;
	}

	public void setUserTagId(Integer userTagId) {
		this.userTagId = userTagId;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

}
