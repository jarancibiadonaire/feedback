package cl.uchile.dcc.feedback.cassandra.test.repositories;

import java.util.List;

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

			String cql="select * from user_session where user_id=1";
			List<UserSession> u=repo.cassandraOperations.select(cql, UserSession.class);
			for(UserSession e:u)
				System.out.println(e.getFeedId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
