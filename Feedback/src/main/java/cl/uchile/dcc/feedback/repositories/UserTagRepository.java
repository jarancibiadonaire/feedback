package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.entities.UserTag;

public interface UserTagRepository extends CrudRepository<UserTag, Integer> {
	
	@Query("select ut.tag from UserTag ut where ut.user.userName = ?1")
	List<Tag> findByUserName(String name);
	List<UserTag> findByUserId(Integer id);
}
