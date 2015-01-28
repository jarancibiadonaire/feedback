package cl.uchile.dcc.feedback.test.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.repositories.CategoryRepository;
import cl.uchile.dcc.feedback.repositories.TagRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class TagRepositoryTest {

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	VisibilityRepository visibilityRepo;
	
	@Autowired
	TagRepository tagRepo;
	
	@Test
	public void test() {
		Visibility v=visibilityRepo.findByTypeIgnoreCase("público");
		Tag t=new Tag();
		t.setName("agua");
		t.setDescription("Temas relacionados con el recurso natural");
		t.setVisibility(v);
		tagRepo.save(t);
		
		Tag t2=tagRepo.findByNameIgnoreCase("agua");
		assertNotNull(t2);
		assertTrue(t2.getDescription().contains("rec"));
		
	}

}
