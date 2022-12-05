package edu.student.system.repository;

import java.util.Optional;

import edu.student.system.domain.Roles;
import edu.student.system.domain.enumeration.RoleName;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Roles entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Boolean existsByName(String roleName);

    Optional<Roles> findDistinctByName(RoleName roleName);
}
