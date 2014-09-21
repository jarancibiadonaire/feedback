package cl.uchile.dcc.feedback.test.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.entities.Role;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.repositories.RoleRepository;
import cl.uchile.dcc.feedback.repositories.SexRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class UserRepositoryTest {

	@Autowired
	SexRepository sexRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Test
	public void test() {
//		Sex s=sexRepo.findByDescription("Hombre").get(0);
//		
//		User u=new User();
//		u.setFirstName("juan pablo");
//		u.setLastName("arancibia donaire");
//		u.setUserName("jarancibia");
//		u.setPassword("jarancibia");
//		u.setEmail("jarancibiadonaire@gmail.com");
//		u.setCreatedDate(new Date());
//		u.setSex(s);
//		userRepo.save(u);
//		
		User u2=userRepo.findByUserName("malila");
		Role r=roleRepo.findOne(2);
		u2.getRoles().add(r);
		userRepo.save(u2);
//		assertNotNull(u2);
//		assertEquals("juan pablo",u2.getFirstName());
		System.out.println(u2.getEmail());
	}

}
