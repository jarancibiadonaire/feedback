package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class NodeVO implements Serializable {

	private static final long serialVersionUID = 3081813673292212076L;
	
	private String name;
	
	private Integer feed;
	
	public NodeVO(String name,Integer feed){
		this.name=name;
		this.feed=feed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFeed() {
		return feed;
	}

	public void setFeed(Integer feed) {
		this.feed = feed;
	}

}
