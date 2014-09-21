package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Origin;

public interface OriginRepository extends CrudRepository<Origin, Integer> {
	List<Origin> findByType(String type);
}
