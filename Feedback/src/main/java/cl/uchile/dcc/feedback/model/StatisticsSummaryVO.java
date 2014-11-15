package cl.uchile.dcc.feedback.model;

import java.io.Serializable;

public class StatisticsSummaryVO implements Serializable {

	private static final long serialVersionUID = 1860742581044143899L;
	
	private StatisticsTagsVO tagsData;
	
	private StatisticsFeedsVO feedsData;
	
	private StatisticsUserVO userData;

	public StatisticsTagsVO getTagsData() {
		return tagsData;
	}

	public void setTagsData(StatisticsTagsVO tagsData) {
		this.tagsData = tagsData;
	}

	public StatisticsFeedsVO getFeedsData() {
		return feedsData;
	}

	public void setFeedsData(StatisticsFeedsVO feedsData) {
		this.feedsData = feedsData;
	}

	public StatisticsUserVO getUserData() {
		return userData;
	}

	public void setUserData(StatisticsUserVO userData) {
		this.userData = userData;
	}

}
