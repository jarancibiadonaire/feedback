package cl.uchile.dcc.feedback.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;

@AnalyzerDef(name = "customanalyzer",
tokenizer = @org.hibernate.search.annotations.TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
	@org.hibernate.search.annotations.TokenFilterDef(factory = LowerCaseFilterFactory.class),
	@org.hibernate.search.annotations.TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "Spanish")
  })
})
@Entity
@Table(name="feed")
@Indexed
public class Feed implements Serializable{

	private static final long serialVersionUID = 5546407254241348577L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feed_id")
	private Integer id;
	
	@Column
	@Field(index=Index.YES, store=Store.NO)
	@Analyzer(definition = "customanalyzer")
	private String title;
	
	@Column
	@Field(index=Index.YES, store=Store.NO)
	@Analyzer(definition = "customanalyzer")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@JoinColumn(name="location_id")
	@OneToOne(cascade=CascadeType.ALL)
	private Location location;
	
	@JoinColumn(name="user_id")
	@OneToOne
	private User user;
	
	@JoinColumn(name="origin")
	@ManyToOne
	private Origin origin;
	
	@JoinColumn(name="visibility")
	@ManyToOne
	private Visibility visibility;

	@OneToMany(mappedBy = "feed")
	private List<Note> notes;
	
	@OneToMany(mappedBy = "feed")
	private List<Media> medias;
	
	@OneToMany(mappedBy = "feed")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "feed")
	private List<Rating> rates;
	
	@OneToMany(mappedBy = "feed")
	private List<FeedTag> feedTags;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Rating> getRates() {
		return rates;
	}

	public void setRates(List<Rating> rates) {
		this.rates = rates;
	}

	public List<FeedTag> getFeedTags() {
		return feedTags;
	}

	public void setFeedTags(List<FeedTag> feedTags) {
		this.feedTags = feedTags;
	}

}
