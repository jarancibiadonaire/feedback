package cl.uchile.dcc.feedback.cassandra.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.cassandra.repositories.UserTagSessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class UserTagSessionTest{

	@Autowired
	private UserTagSessionRepository repo;

	@Test
	public void test() {
		try {

			List<Integer> tags=new ArrayList<Integer>();
			tags.add(6);
			tags.add(7);
			tags.add(8);
			tags.add(9);
			repo.addTagsToUser(tags, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
