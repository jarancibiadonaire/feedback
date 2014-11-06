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
	static public enum Origin_enum{
		FEEDBACK(1),FACEBOOK(2),TWITTER(3),FOURSQUARE(4);
		private int id;
		private Origin_enum(int id){
			this.id=id;
		}
		public int getId(){
			return id;	
		}
	}
	Integer createFeed(FeedVO feed);
	List<VisibilityVO> getVisibilities();
	List<FeedVO> getAllFeeds();
	Integer commentFeed(CommentVO commentVO);
	FeedVO findFeedById(Integer id);
	Integer voteFeed(RatingVO rating);
	List<TagVO> getAllTags();
	CommentVO findCommentById(Integer id);
	FeedGraphVO getFeedGraph();
	Integer addTags(FeedVO feedVO);
	List<FeedVO> searchFeedsByText(String text);

}
