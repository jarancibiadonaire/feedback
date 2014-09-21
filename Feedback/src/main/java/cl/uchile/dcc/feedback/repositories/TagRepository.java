package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {
	List<Tag> findByName(String name);
	List<Tag> findByNameIgnoreCase(String name);
	List<Tag> findByDescription(String name);
	List<Tag> findByDescriptionContainingIgnoreCase(String name);
}
