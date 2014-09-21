package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Sex;
import cl.uchile.dcc.feedback.repositories.SexRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class SexRepositoryTest {

	@Autowired 
	SexRepository sexRepo;
	
	@Test
	public void test() {
		List<Sex> s2=sexRepo.findByDescription("Hombre");
		assertNotNull(s2);
		assertEquals(s2.size(),1);
		System.out.println(s2.get(0).getId());
	}

}
