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

@Entity
@Table(name="media")
public class Media implements Serializable{

	private static final long serialVersionUID = -2018132579205140572L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_id")
	private Integer id;
	
	@JoinColumn(name="feed")
	@ManyToOne
	private Feed feed;
	
	@Column(name="content_type")
	private String contentType;
	
	@Column(name="path")
	private String path;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_date")
	private Date uploadDate;
	
	@JoinColumn(name="visibility")
	@OneToOne
	private Visibility visibility;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
