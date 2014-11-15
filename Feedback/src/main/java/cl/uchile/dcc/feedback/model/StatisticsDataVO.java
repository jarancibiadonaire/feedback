package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class StatisticsDataVO implements Serializable {

	private static final long serialVersionUID = -361786573953518403L;

	private String data;

	private Integer value;
	
	private Boolean control;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Boolean getControl() {
		return control;
	}

	public void setControl(Boolean control) {
		this.control = control;
	}

}
