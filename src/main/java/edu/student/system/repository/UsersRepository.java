package edu.student.system.repository;

import java.util.Optional;

import edu.student.system.domain.Users;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Users entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);

    Boolean existsByLogin(String login);

}
