package cl.uchile.dcc.feedback.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
	List<Rating> findByFeedId(Integer id);
	List<Rating> findByUserId(Integer id);
	List<Rating> findByCreatedDateBetween(Date start,Date end);
	@Query("select distinct r.feed.id from Rating r where r.user.id=?1")
	List<Integer> findFeedsIdsRatingByUserId(Integer id);
 }
