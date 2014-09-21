package cl.uchile.dcc.feedback.services;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.entities.Feed;

@Component
public interface FeedServiceRemote {
	static public enum Visibility_enum{
		PRIVATE(1),PUBLIC(2);
		private int id;
		private Visibility_enum(int id){
			this.id=id;
		}
		public int getId(){
			return id;	
		}
	}
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
	Integer createFeed(Feed feed);

}
