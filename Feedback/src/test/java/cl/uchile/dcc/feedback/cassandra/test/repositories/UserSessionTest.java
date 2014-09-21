package cl.uchile.dcc.feedback.cassandra.test.repositories;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.cassandra.entities.UserSession;
import cl.uchile.dcc.feedback.cassandra.repositories.UserSessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class UserSessionTest{

	@Autowired
	private UserSessionRepository repo;

	@Test
	public void test() {
		try {

			UserSession us=new UserSession();
			us.setUserId(1);
			us.setSessionId(1);
			us.setTime(new Date());
			us.setFeedId(4);
			repo.cassandraOperations.insert(us);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
