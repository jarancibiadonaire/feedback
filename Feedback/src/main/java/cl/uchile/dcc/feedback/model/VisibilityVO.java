package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class VisibilityVO implements Serializable{

	private static final long serialVersionUID = 3609163801105232232L;
	
	private Integer id;
	private String visibility;
	
	public VisibilityVO(){
		
	}
	public VisibilityVO(Integer id,String visibility){
		this.id=id;
		this.visibility=visibility;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

}
