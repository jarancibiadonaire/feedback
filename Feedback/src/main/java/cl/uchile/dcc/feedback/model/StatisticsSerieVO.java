package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class StatisticsSerieVO implements Serializable {

	private static final long serialVersionUID = 5114814218686976669L;
	
	private String name;
	
	private List<StatisticsDataVO> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StatisticsDataVO> getValues() {
		return values;
	}

	public void setValues(List<StatisticsDataVO> values) {
		this.values = values;
	}

}
