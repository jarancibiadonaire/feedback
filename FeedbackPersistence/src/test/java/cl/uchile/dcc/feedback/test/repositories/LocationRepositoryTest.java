package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Comuna;
import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.repositories.ComunaRepository;
import cl.uchile.dcc.feedback.repositories.LocationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class LocationRepositoryTest {

	@Autowired
	ComunaRepository comunaRepo;
	
	@Autowired
	LocationRepository locationRepo;
	
	@Test
	public void test() {
		Comuna c=comunaRepo.findByName("Macul");
		
		Location l=new Location();
		l.setLat(new BigDecimal("-70.22222"));
		l.setLng(new BigDecimal("-30.11111"));
		l.setComuna(c);
		l.setCreatedDate(new Date());
		l.setAddress("camino agrícola");
		locationRepo.save(l);
		
		List<Location> l2=locationRepo.findByAddress("camino agrícola");
		assertNotNull(l2);
		assertEquals(1,l2.size());
		System.out.println(l2.get(0).toString());
	}

}
