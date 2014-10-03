package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Origin;
import cl.uchile.dcc.feedback.repositories.OriginRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class OriginRepositoryTest {
	@Autowired
	OriginRepository originRepo;
	
	@Test
	public void test() {
		Origin o=new Origin();
		o.setType("Foursquare");
		originRepo.save(o);
		
		Origin o2=originRepo.findByType("Foursquare");
		assertNotNull(o2);
		System.out.println(o2.getId());		
	}

}
