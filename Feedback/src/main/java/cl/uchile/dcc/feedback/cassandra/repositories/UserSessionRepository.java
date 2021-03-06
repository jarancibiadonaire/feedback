package cl.uchile.dcc.feedback.cassandra.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component
public class UserSessionRepository {
	@Autowired
	@Qualifier(value="cassandraTemplate")
	public CassandraOperations cassandraOperations;
	
	
}
