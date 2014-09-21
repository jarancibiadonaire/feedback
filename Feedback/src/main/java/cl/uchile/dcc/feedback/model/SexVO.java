package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class SexVO implements Serializable {

	private static final long serialVersionUID = 632924858160376327L;
	
	private Integer id;
	
	private String sex;
	
	public SexVO(Integer id,String sex){
		this.id=id;
		this.sex=sex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
