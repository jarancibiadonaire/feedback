package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class StatisticsTagsVO implements Serializable {

	private static final long serialVersionUID = -3484196963157005072L;
	
	private List<StatisticsDataVO> tagsFrecuency;
	
	private List<StatisticsDataVO> tagsCommented;
	
	private List<StatisticsSerieVO> tagsRated;

	public List<StatisticsDataVO> getTagsFrecuency() {
		return tagsFrecuency;
	}

	public void setTagsFrecuency(List<StatisticsDataVO> tagsFrecuency) {
		this.tagsFrecuency = tagsFrecuency;
	}

	public List<StatisticsDataVO> getTagsCommented() {
		return tagsCommented;
	}

	public void setTagsCommented(List<StatisticsDataVO> tagsCommented) {
		this.tagsCommented = tagsCommented;
	}

	public List<StatisticsSerieVO> getTagsRated() {
		return tagsRated;
	}

	public void setTagsRated(List<StatisticsSerieVO> tagsRated) {
		this.tagsRated = tagsRated;
	}

}
