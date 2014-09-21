package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	List<Category> findByNameIgnoreCase(String name);
}
