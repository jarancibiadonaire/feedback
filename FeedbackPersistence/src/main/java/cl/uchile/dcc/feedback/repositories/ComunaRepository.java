package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Comuna;

public interface ComunaRepository extends CrudRepository<Comuna, Integer> {
	Comuna findByName(String name);
	List<Comuna> findByRegionName(String name);
	List<Comuna> findByRegionNumber(String number);
	Comuna findByNameIgnoreCase(String name);
	List<Comuna> findByRegionNameIgnoreCase(String name);
	List<Comuna> findByRegionNumberIgnoreCase(String number);

}
