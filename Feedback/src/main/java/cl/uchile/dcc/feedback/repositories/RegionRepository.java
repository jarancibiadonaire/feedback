package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Region;

public interface RegionRepository extends CrudRepository<Region, Integer> {	
	List<Region> findByName(String name);
	List<Region> findByNameContainingIgnoreCase(String name);
	List<Region> findByNumber(String number);
	List<Region> findByNumberContainingIgnoreCase(String number);
	List<Region> findByNameOrNumber(String name,String number);

}
