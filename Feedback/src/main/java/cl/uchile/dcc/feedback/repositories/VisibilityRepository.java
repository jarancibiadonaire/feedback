package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Visibility;

public interface VisibilityRepository extends
		CrudRepository<Visibility, Integer> {

	List<Visibility> findByType(String type);
	List<Visibility> findByTypeIgnoreCase(String type);
}
