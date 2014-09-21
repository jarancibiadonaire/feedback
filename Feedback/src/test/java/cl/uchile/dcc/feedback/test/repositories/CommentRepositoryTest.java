package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Comment;
import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.repositories.CommentRepository;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class CommentRepositoryTest {

	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CommentRepository commentRepo;
	
	@Test
	public void test() {
		Feed f=feedRepo.findOne(1);
		User u=userRepo.findOne(1);
		Comment c=new Comment();
		c.setComment("nuevo comentario");
		c.setCreatedDate(new Date());
		c.setFeed(f);
		c.setUser(u);
		c.setLevel(0);		
		commentRepo.save(c);
		Comment c2=commentRepo.findOne(23);
		assertNotNull(c2);
		assertTrue(c2.getComment().contains("comentario"));
		
	}

}
