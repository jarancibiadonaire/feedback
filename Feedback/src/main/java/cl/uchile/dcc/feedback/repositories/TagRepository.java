package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {
	Tag findByName(String name);
	Tag findByNameIgnoreCase(String name);
	List<Tag> findByDescription(String name);
	List<Tag> findByDescriptionContainingIgnoreCase(String name);
	List<Tag> findByVisibilityId(Integer id);
	List<Tag> findByIdIn(List<Integer> ids);
}
