package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class TagVO implements Serializable {

	private static final long serialVersionUID = -1283064462275897757L;
	
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String visibility;
	
	private Boolean rootTag;
	
	private Boolean byOwner;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Boolean getRootTag() {
		return rootTag;
	}

	public void setRootTag(Boolean rootTag) {
		this.rootTag = rootTag;
	}

	public Boolean getByOwner() {
		return byOwner;
	}

	public void setByOwner(Boolean byOwner) {
		this.byOwner = byOwner;
	}


}
