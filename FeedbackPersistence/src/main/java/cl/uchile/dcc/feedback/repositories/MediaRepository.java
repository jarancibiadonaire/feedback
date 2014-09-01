package cl.uchile.dcc.feedback.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Media;

public interface MediaRepository extends CrudRepository<Media, Integer> {
	List<Media> findByFeedId(Integer id);
	List<Media> findByFeedIdAndVisibilityId(Integer id,Integer vid);
	List<Media> findByUploadDateBetween(Date start,Date end);
	List<Media> findByVisibilityId(Integer id);
}
