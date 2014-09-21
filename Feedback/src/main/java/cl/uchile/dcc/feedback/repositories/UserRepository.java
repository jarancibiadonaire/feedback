package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {	
	User findByUserName(String userName);
	User findByEmail(String email);
	List<User> findByUserNameOrEmail(String userName,String email);
}
