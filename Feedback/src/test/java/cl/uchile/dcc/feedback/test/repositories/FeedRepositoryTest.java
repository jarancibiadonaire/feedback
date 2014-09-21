package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
public class FeedRepositoryTest {

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

	@Test
	public void test() {
		Location l=locationRepo.findOne(1);
		User u=userRepo.findOne(1);
		Origin o=originRepo.findOne(1);
		Visibility v=visibilityRepo.findOne(1);
		
		Feed f=new Feed();
		f.setTitle("nuevo feed");
		f.setDescription("nueva descripción");
		f.setCreatedDate(new Date());
		f.setLocation(l);
		f.setUser(u);
		f.setVisibility(v);
		f.setOrigin(o);
		feedRepo.save(f);
		
		Feed f2=feedRepo.findOne(20);
		assertNotNull(f2);
		assertEquals("nuevo feed",f2.getTitle());
	}

}
