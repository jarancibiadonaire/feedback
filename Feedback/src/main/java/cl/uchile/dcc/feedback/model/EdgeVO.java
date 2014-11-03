package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class EdgeVO implements Serializable {

	private static final long serialVersionUID = 2702778573353332961L;
	
	private String name;
	
	private List<String> edges;

	public EdgeVO(String name,List<String> edges){
		this.name=name;
		this.edges=edges;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getEdges() {
		return edges;
	}

	public void setEdges(List<String> edges) {
		this.edges = edges;
	}

}
