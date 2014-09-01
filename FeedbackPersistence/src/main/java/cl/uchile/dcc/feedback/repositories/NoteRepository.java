package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Note;

public interface NoteRepository extends CrudRepository<Note, Integer> {
	List<Note> findByNoteContaining(String note);
	List<Note> findByNoteContainingIgnoreCase(String note);
	List<Note> findByFeedId(Integer id);
	List<Note> findByFeedIdAndVisibilityId(Integer id,Integer vid);
	List<Note> findByUserId(Integer id);
	List<Note> findByUserIdAndVisibilityId(Integer id,Integer vid);
}
