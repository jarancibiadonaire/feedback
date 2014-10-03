package cl.uchile.dcc.feedback.services;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.FeedVO;
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

}
