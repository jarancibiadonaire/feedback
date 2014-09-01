package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.Rating;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.RatingRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class RatingRepositoryTest {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	RatingRepository ratingRepo;
	
	@Test
	public void test() {
		User u=userRepo.findOne(1);
		Feed f=feedRepo.findOne(1);
		Rating r=new Rating();
		r.setScore(5);
		r.setFeed(f);
		r.setUser(u);
		r.setCreatedDate(new Date());
		ratingRepo.save(r);
		Rating r2=ratingRepo.findOne(24);
		assertNotNull(r2);
		assertEquals(1,r2.getFeed().getId().intValue());
		
	}

}
