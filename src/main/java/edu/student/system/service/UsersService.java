package edu.student.system.service;

import edu.student.system.service.dto.UsersDTO;
import edu.student.system.web.rest.vm.UserDetail;
import edu.student.system.web.rest.vm.UserDetailVM;
import edu.student.system.web.rest.vm.UserVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link edu.student.system.domain.Users}.
 */
public interface UsersService {
  /**
   * Save a users.
   *
   * @param userDetail the entity to save.
   * @return the persisted entity.
   */
  UsersDTO save(UserDetail userDetail) throws Exception;

  /**
   * Updates a users.
   *
   * @param userDetail the entity to update.
   * @return the persisted entity.
   */
  UserDetail update(UserDetail userDetail) throws Exception;

  /**
   * Get all the users.
   *
   * @return the list of entities.
   */
  List<UserVM> findAll();

  /**
   * Get the "id" users.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<UserDetailVM> findOne(Long id);

  /**
   * Delete the "id" users.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  String authenticate(String username, String password, Boolean isRemember);

}
