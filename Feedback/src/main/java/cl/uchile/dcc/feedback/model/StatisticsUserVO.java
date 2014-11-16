package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class StatisticsUserVO implements Serializable {

	private static final long serialVersionUID = -2735673079293955619L;
	
	private List<StatisticsDataVO> userFreq;

	public List<StatisticsDataVO> getUserFreq() {
		return userFreq;
	}

	public void setUserFreq(List<StatisticsDataVO> userFreq) {
		this.userFreq = userFreq;
	}

}
