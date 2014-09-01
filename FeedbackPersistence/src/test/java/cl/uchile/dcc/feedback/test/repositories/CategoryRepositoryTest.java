package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Category;
import cl.uchile.dcc.feedback.repositories.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepo;
	
	@Test
	public void test() {
		Category c=new Category();
		c.setName("Salud");
		c.setCreatedDate(new Date());
		categoryRepo.save(c);
		
		List<Category> c2=categoryRepo.findByNameIgnoreCase("salud");
		assertNotNull(c2);
		assertTrue(c2.get(0).getName().contains("d"));
	}

}
