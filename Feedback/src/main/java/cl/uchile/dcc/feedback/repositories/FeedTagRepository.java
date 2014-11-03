package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.FeedTag;

public interface FeedTagRepository extends CrudRepository<FeedTag, Integer> {
	
	List<FeedTag> findByTagIdAndTagVisibilityId(Integer id,Integer vid);

}
