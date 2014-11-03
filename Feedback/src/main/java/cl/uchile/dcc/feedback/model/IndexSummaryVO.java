package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class IndexSummaryVO implements Serializable {

	private static final long serialVersionUID = 807286826543746253L;
	
	private Integer totalUsers;
	
	private Integer totalFeeds;
	
	private Integer totalComments;
	
	private Integer totalTags;
	
	public IndexSummaryVO(Integer users,Integer feeds,Integer comments,Integer tags){
		this.totalUsers=users;
		this.totalFeeds=feeds;
		this.totalComments=comments;
		this.totalTags=tags;
	}

	public Integer getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Integer totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Integer getTotalFeeds() {
		return totalFeeds;
	}

	public void setTotalFeeds(Integer totalFeeds) {
		this.totalFeeds = totalFeeds;
	}

	public Integer getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(Integer totalComments) {
		this.totalComments = totalComments;
	}

	public Integer getTotalTags() {
		return totalTags;
	}

	public void setTotalTags(Integer totalTags) {
		this.totalTags = totalTags;
	}

}
