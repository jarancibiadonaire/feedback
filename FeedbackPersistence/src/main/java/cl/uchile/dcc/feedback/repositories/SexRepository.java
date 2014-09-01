package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Sex;

public interface SexRepository extends CrudRepository<Sex, Integer> {	
	List<Sex> findByDescription(String description);
}
