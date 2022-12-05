package edu.student.system.repository;

import java.util.List;
import java.util.Optional;

import edu.student.system.domain.Users;
import edu.student.system.domain.enumeration.RoleName;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Users entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.activated = true " +
        " and lower( u.login ) = lower( :username )")
    List<Users> checkLoginUser(@Param("username") String username);

    List<Users> findAllByRoleNameIn(List<RoleName> roles);

    List<Users> findAllByIdAndRoleNameIn(Long id, List<RoleName> roles);

    Optional<Users> findById(Long id);
}
