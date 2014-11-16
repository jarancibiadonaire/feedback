package cl.uchile.dcc.feedback.model;

import java.io.Serializable;
import java.util.List;

public class StatisticsFeedsVO implements Serializable {

	private static final long serialVersionUID = -2767275856057188635L;
	
	private List<StatisticsDataVO> commentedFeed;
	
	private List<StatisticsSerieVO> ratedFeed;

	public List<StatisticsDataVO> getCommentedFeed() {
		return commentedFeed;
	}

	public void setCommentedFeed(List<StatisticsDataVO> commentedFeed) {
		this.commentedFeed = commentedFeed;
	}

	public List<StatisticsSerieVO> getRatedFeed() {
		return ratedFeed;
	}

	public void setRatedFeed(List<StatisticsSerieVO> ratedFeed) {
		this.ratedFeed = ratedFeed;
	}

}
