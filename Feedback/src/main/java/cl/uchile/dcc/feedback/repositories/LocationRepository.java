package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {
	List<Location> findByAddress(String address);
}
