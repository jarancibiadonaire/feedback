package cl.uchile.dcc.feedback.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Origin;

public interface OriginRepository extends CrudRepository<Origin, Integer> {
	Origin findByType(String type);
	Origin findByTypeIgnoreCase(String type);
}
