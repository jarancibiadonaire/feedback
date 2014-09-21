package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.Media;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.MediaRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class MediaRepositoryTest {

	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	VisibilityRepository visibilityRepo;
	
	@Autowired
	MediaRepository mediaRepo;
	
	@Test
	public void test() {
		Feed f=feedRepo.findOne(1);
		Visibility v=visibilityRepo.findOne(1);
		Media m=new Media();
		m.setPath("/user/tmp");
		m.setUploadDate(new Date());
		m.setFeed(f);
		m.setVisibility(v);
		m.setContentType("application/pdf");
		
		mediaRepo.save(m);
		
		Media m2=mediaRepo.findOne(22);
		assertNotNull(m2);
		assertTrue(m2.getContentType().contains("pdf"));
				
	}

}
