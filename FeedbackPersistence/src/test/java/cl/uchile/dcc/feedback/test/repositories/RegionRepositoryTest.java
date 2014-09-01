package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Region;
import cl.uchile.dcc.feedback.repositories.RegionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class RegionRepositoryTest {

	@Autowired
	RegionRepository regionRepo;
	
	@Test
	public void test() {
		Region r=new Region();
		r.setName("Coquimbo");
		r.setNumber("IV");
		regionRepo.save(r);
		
		List<Region> r2=regionRepo.findByNameOrNumber("", "IV");
		assertNotNull(r2);
		assertEquals(1,r2.size());
		System.out.println(r2.get(0).getId());
	}

}
