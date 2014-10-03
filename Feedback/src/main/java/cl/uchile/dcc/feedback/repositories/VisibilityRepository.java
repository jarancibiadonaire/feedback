package cl.uchile.dcc.feedback.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Visibility;

public interface VisibilityRepository extends
		CrudRepository<Visibility, Integer> {

	Visibility findByType(String type);
	Visibility findByTypeIgnoreCase(String type);
}
