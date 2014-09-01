package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.FeedTag;
import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.FeedTagRepository;
import cl.uchile.dcc.feedback.repositories.TagRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
public class FeedTagRepositoryTest {

	@Autowired
	FeedRepository feedRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TagRepository tagRepo;
	@Autowired
	VisibilityRepository visibilityRepo;
	@Autowired
	FeedTagRepository feedTagRepo;
	
	@Test
	public void test() {
		User u=userRepo.findOne(1);
		Feed f=feedRepo.findOne(1);
		Tag t=tagRepo.findOne(2);
		Visibility v=visibilityRepo.findOne(1);
		FeedTag ft=new FeedTag();
		ft.setFeed(f);
		ft.setTag(t);
		ft.setUser(u);
		ft.setVisibility(v);
		ft.setCreatedDate(new Date());
		feedTagRepo.save(ft);
		
		FeedTag ft2=feedTagRepo.findOne(27);
		assertNotNull(ft2);
		assertEquals(2,ft2.getTag().getId().intValue());
	}

}
