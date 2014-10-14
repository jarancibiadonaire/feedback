package cl.uchile.dcc.feedback.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Feed;

public interface FeedRepository extends CrudRepository<Feed, Integer> {
	List<Feed> findByTitleContaining(String title);
	List<Feed> findByTitleContainingIgnoreCase(String title);
	List<Feed> findByDescriptionContaining(String description);
	List<Feed> findByDescriptionContainingIgnoreCase(String description);
	List<Feed> findByCreatedDateBetween(Date start, Date end);
	List<Feed> findByLocationAddress(String name);
	List<Feed> findByLocationAddressContaining(String name);
	List<Feed> findByLocationAddressContainingIgnoreCase(String name);
	List<Feed> findByLocationComunaName(String name);
	List<Feed> findByLocationComunaNameIgnoreCase(String name);
	List<Feed> findByLocationComunaRegionName(String name);
	List<Feed> findByLocationComunaRegionNameIgnoreCase(String name);
	List<Feed> findByUserUserName(String username);
	List<Feed> findByUserUserNameIgnoreCase(String username);
	List<Feed> findByUserId(Integer id);
	List<Feed> findByUserIdAndVisibilityId(Integer id,Integer vid);
	List<Feed> findByOriginId(Integer id);
	List<Feed> findByVisibilityIdOrderByCreatedDateDesc(Integer id);
}
