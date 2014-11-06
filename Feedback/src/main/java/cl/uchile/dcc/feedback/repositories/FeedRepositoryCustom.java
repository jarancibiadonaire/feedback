package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import cl.uchile.dcc.feedback.model.FeedVO;

public interface FeedRepositoryCustom {

	public List<FeedVO> searchFeedsByTextOpt(String text);

	List<FeedVO> getFeedsOpt();

	FeedVO getFeedOpt(Integer id);
}
