package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.Note;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.NoteRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class NoteRepositoryTest {

	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	VisibilityRepository visibilityRepo;
	
	@Autowired
	NoteRepository noteRepo;
	
	@Test
	public void test() {
		User u=userRepo.findOne(1);
		Feed f=feedRepo.findOne(1);
		Visibility v=visibilityRepo.findOne(1);
		Note n=new Note();
		n.setNote("esta es la primera nota");
		n.setCreatedDate(new Date());
		n.setFeed(f);
		n.setUser(u);
		n.setVisibility(v);
		
		noteRepo.save(n);
		
		Note n2=noteRepo.findOne(21);
		assertNotNull("jarancibia", n2.getUser().getUserName());
	}

}
