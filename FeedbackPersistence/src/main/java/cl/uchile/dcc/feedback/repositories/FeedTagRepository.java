package cl.uchile.dcc.feedback.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.FeedTag;

public interface FeedTagRepository extends CrudRepository<FeedTag, Integer> {

}
