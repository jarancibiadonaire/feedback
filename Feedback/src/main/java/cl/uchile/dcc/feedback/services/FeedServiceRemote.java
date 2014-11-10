package cl.uchile.dcc.feedback.services;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.FeedGraphVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.RatingVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.model.VisibilityVO;

@Component
public interface FeedServiceRemote {
	
	Integer createFeed(FeedVO feed);
	List<VisibilityVO> getVisibilities();
	List<FeedVO> getAllFeeds();
	Integer commentFeed(CommentVO commentVO);
	FeedVO findFeedById(Integer id);
	Integer voteFeed(RatingVO rating);
	List<TagVO> getAllTags();
	CommentVO findCommentById(Integer id);
	FeedGraphVO getFeedGraph(List<Integer> feeds);
	Integer addTags(FeedVO feedVO);
	List<FeedVO> searchFeedsByText(String text);
	List<Integer> getFeedsIdsRatingByUsername(String username);
	List<Integer> getTagsByUsername(String username);
	void configFollowTags(String username, List<Integer> ids);
	List<Integer> getFollowingTags(String username);

}
