package ohjelmistoprojekti1.kyselylomake.domain;

import org.springframework.data.repository.CrudRepository;

import ohjelmistoprojekti1.kyselylomake.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}