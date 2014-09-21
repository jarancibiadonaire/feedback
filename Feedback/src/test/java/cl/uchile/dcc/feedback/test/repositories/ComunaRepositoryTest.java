package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Comuna;
import cl.uchile.dcc.feedback.entities.Region;
import cl.uchile.dcc.feedback.repositories.ComunaRepository;
import cl.uchile.dcc.feedback.repositories.RegionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class ComunaRepositoryTest {

	@Autowired
	RegionRepository regionRepo;
	@Autowired
	ComunaRepository comunaRepo;
	
	@Test
	public void test() {
		Region r=regionRepo.findByNumber("XIII").get(0);
		
		Comuna c=new Comuna();
		c.setName("Talagante");
		c.setRegion(r);
		comunaRepo.save(c);
		
		Comuna c2=comunaRepo.findByName("Talagante");
		assertNotNull(c2);
		assertEquals("XIII",c2.getRegion().getNumber());
	}

}
