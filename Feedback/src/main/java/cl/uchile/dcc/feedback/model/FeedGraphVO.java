package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class FeedGraphVO implements Serializable {

	private static final long serialVersionUID = -3585517782124725187L;
	
	private List<NodeVO> nodes;
	
	private List<EdgeVO> edges;

	public List<NodeVO> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeVO> nodes) {
		this.nodes = nodes;
	}

	public List<EdgeVO> getEdges() {
		return edges;
	}

	public void setEdges(List<EdgeVO> edges) {
		this.edges = edges;
	}

}
