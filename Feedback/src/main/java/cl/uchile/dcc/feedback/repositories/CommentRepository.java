package cl.uchile.dcc.feedback.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
	List<Comment> findByFeedId(Integer id);
	List<Comment> findByUserId(Integer id);
	List<Comment> findByCreatedDateBetween(Date start,Date end);
	List<Comment> findByResponseToId(Integer id);
	List<Comment> findByCommentContaining(String comment);
}
