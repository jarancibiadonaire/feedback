package cl.uchile.dcc.feedback.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.services.FeedServiceRemote;
import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.entities.Origin;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.LocationRepository;
import cl.uchile.dcc.feedback.repositories.OriginRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@Component
public class FeedService implements FeedServiceRemote {

	@Autowired
	FeedRepository feedRepo;
	@Autowired
	LocationRepository locationRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	OriginRepository originRepo;
	@Autowired
	VisibilityRepository visibilityRepo;
	
	@Override
	public Integer createFeed(Feed feed){
		if(feed==null)
			return null;
		Location l=locationRepo.findOne(1);
		User u=userRepo.findOne(1);
		Origin o=originRepo.findOne(1);
		Visibility v=visibilityRepo.findOne(1);
		feed.setLocation(l);
		feed.setUser(u);
		feed.setVisibility(v);
		feed.setOrigin(o);
		feed.setCreatedDate(new Date());
		feedRepo.save(feed);
		return feed.getId();
	}
}
