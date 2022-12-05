package edu.student.system.service.impl;

import edu.student.system.domain.Roles;
import edu.student.system.domain.enumeration.RoleName;
import edu.student.system.repository.RolesRepository;
import edu.student.system.service.RolesService;
import edu.student.system.service.dto.RolesDTO;
import edu.student.system.service.mapper.RolesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {

  private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

  private final RolesRepository rolesRepository;

  private final RolesMapper rolesMapper;

  public RolesServiceImpl(
    RolesRepository rolesRepository,
    RolesMapper rolesMapper
  ) {
    this.rolesRepository = rolesRepository;
    this.rolesMapper = rolesMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public List<RolesDTO> findAll() {
    log.debug("Request to get all Roles");
    return rolesRepository
      .findAll()
      .stream()
      .map(rolesMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Roles> findByRoleName(RoleName roleName) {
    log.debug("Request to get Roles : {}", roleName);
    return rolesRepository.findDistinctByName(roleName);
  }

}
