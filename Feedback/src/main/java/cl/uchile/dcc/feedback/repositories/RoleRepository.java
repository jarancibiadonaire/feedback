package cl.uchile.dcc.feedback.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.uchile.dcc.feedback.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByRole(String role);
}
