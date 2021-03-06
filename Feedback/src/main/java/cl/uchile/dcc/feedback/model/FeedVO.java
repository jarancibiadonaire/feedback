package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedVO implements Serializable {
	
	private static final long serialVersionUID = 2937430800550649887L;

	private Integer id;

	private String title;

	private String description;

	private Date createdDate;

	private LocationVO location;

	private String user;
	
	private String origin;

	private String visibility;

	private List<NoteVO> notes;
	
	private List<Integer> medias;
	
	private List<CommentVO> comments;
	
	private List<String> tags;
	
	private List<RatingVO> ratings;
	
	private Integer totalComments;
	
	private Integer totalLikes;
	
	private Integer totalDislikes;
	
	private List<TagVO> tagsData;
	
	private List<TagVO> othersTagsData;
	
	public FeedVO(){
		this.location=new LocationVO();
	}

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

	public LocationVO getLocation() {
		return location;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public List<NoteVO> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteVO> notes) {
		this.notes = notes;
	}

	public List<Integer> getMedias() {
		return medias;
	}

	public void setMedias(List<Integer> medias) {
		this.medias = medias;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	public List<String> getTags() {
		if(this.tags==null)
			return new ArrayList<String>();
		else
			return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<RatingVO> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingVO> ratings) {
		this.ratings = ratings;
	}

	public Integer getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(Integer totalComments) {
		this.totalComments = totalComments;
	}

	public Integer getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(Integer totalLikes) {
		this.totalLikes = totalLikes;
	}

	public Integer getTotalDislikes() {
		return totalDislikes;
	}

	public void setTotalDislikes(Integer totalDislikes) {
		this.totalDislikes = totalDislikes;
	}

	public List<TagVO> getTagsData() {
		return tagsData;
	}

	public void setTagsData(List<TagVO> tagsData) {
		this.tagsData = tagsData;
	}

	public List<TagVO> getOthersTagsData() {
		return othersTagsData;
	}

	public void setOthersTagsData(List<TagVO> othersTagsData) {
		this.othersTagsData = othersTagsData;
	}
	
}
