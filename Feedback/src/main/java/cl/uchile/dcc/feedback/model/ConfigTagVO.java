package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class ConfigTagVO implements Serializable {

	private static final long serialVersionUID = -5090413551500199792L;
	
	private String username;
	
	private List<Integer> tagsIds;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Integer> getTagsIds() {
		return tagsIds;
	}
	public void setTagsIds(List<Integer> tagsIds) {
		this.tagsIds = tagsIds;
	}

}
