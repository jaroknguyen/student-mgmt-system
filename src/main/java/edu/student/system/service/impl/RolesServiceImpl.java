package edu.student.system.service.impl;

import edu.student.system.domain.Roles;
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
  public RolesDTO save(RolesDTO rolesDTO) {
    log.debug("Request to save Roles : {}", rolesDTO);
    Roles roles = rolesMapper.toEntity(rolesDTO);
    roles = rolesRepository.save(roles);
    return rolesMapper.toDto(roles);
  }

  @Override
  public RolesDTO update(RolesDTO rolesDTO) {
    log.debug("Request to update Roles : {}", rolesDTO);
    Roles roles = rolesMapper.toEntity(rolesDTO);
    roles = rolesRepository.save(roles);
    return rolesMapper.toDto(roles);
  }

  @Override
  public Optional<RolesDTO> partialUpdate(RolesDTO rolesDTO) {
    log.debug("Request to partially update Roles : {}", rolesDTO);

    return rolesRepository
      .findById(rolesDTO.getId())
      .map(existingRoles -> {
        rolesMapper.partialUpdate(existingRoles, rolesDTO);

        return existingRoles;
      })
      .map(rolesRepository::save)
      .map(rolesMapper::toDto);
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
  public Optional<RolesDTO> findOne(Long id) {
    log.debug("Request to get Roles : {}", id);
    return rolesRepository.findById(id).map(rolesMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Roles : {}", id);
    rolesRepository.deleteById(id);
  }
}
