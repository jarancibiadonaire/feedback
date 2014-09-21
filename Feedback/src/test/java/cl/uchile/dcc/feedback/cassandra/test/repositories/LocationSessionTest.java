package cl.uchile.dcc.feedback.cassandra.test.repositories;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.cassandra.entities.LocationSession;
import cl.uchile.dcc.feedback.cassandra.repositories.LocationSessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class LocationSessionTest{

	@Autowired
	private LocationSessionRepository repo;

	@Test
	public void test() {
		try {

			LocationSession us=new LocationSession();
			us.setLocationId(1);
			us.setSessionId(1);
			us.setTime(new Date());
			us.setFeedId(3);
			repo.cassandraOperations.insert(us);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
