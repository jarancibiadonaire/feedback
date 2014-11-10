package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.uchile.dcc.feedback.entities.FeedTag;

public interface FeedTagRepository extends CrudRepository<FeedTag, Integer> {
	
	List<FeedTag> findByTagIdAndTagVisibilityId(Integer id,Integer vid);
	FeedTag findByFeedIdAndTagId(Integer fid,Integer tid);
	@Query("select distinct ft.tag.id from FeedTag ft where ft.user.id=:id")
	List<Integer> findByUserId(@Param("id") Integer id);
}
