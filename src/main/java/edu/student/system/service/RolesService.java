package edu.student.system.service;

import edu.student.system.domain.Roles;
import edu.student.system.domain.enumeration.RoleName;
import edu.student.system.service.dto.RolesDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link edu.student.system.domain.Roles}.
 */
public interface RolesService {

  /**
   * Get all the roles.
   *
   * @return the list of entities.
   */
  List<RolesDTO> findAll();

  /**
   * Get the "id" roles.
   *
   * @param roleName the id of the entity.
   * @return the entity.
   */
  Optional<Roles> findByRoleName(RoleName roleName);

}
