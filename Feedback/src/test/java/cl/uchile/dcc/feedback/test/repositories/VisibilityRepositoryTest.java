package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class VisibilityRepositoryTest {

	@Autowired
	VisibilityRepository visibilityRepo;
	
	@Test
	public void test() {
		Visibility v=new Visibility();
		v.setType("Público");
		visibilityRepo.save(v);
		
		List<Visibility> v2=visibilityRepo.findByType("Público");
		assertNotNull(v2);
		assertEquals(v2.size(),1);
		System.out.println(v2.get(0).getId());
	}

}
