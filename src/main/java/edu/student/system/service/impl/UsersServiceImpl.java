package edu.student.system.service.impl;

import edu.student.system.domain.Roles;
import edu.student.system.domain.Users;
import edu.student.system.domain.enumeration.RoleName;
import edu.student.system.repository.UsersRepository;
import edu.student.system.security.UserNotActivatedException;
import edu.student.system.security.jwt.TokenProvider;
import edu.student.system.service.RolesService;
import edu.student.system.service.UsersService;
import edu.student.system.service.dto.UsersDTO;
import edu.student.system.service.mapper.UsersMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.student.system.web.rest.vm.UserDetail;
import edu.student.system.web.rest.vm.UserDetailVM;
import edu.student.system.web.rest.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Users}.
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {

  private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

  private final UsersRepository usersRepository;

  private final UsersMapper usersMapper;

  private final TokenProvider tokenProvider;

  private final RolesService rolesService;

  @Autowired
  @Qualifier("PwdEncoder")
  private PasswordEncoder passwordEncoder;

  @Autowired @Lazy
  @Qualifier("RealRoleFromContext")
  private List<String> currentRoleName;

  public UsersServiceImpl(
      UsersRepository usersRepository,
      UsersMapper usersMapper,
      TokenProvider tokenProvider,
      RolesService rolesService) {
    this.usersRepository = usersRepository;
    this.usersMapper = usersMapper;
    this.tokenProvider = tokenProvider;
    this.rolesService = rolesService;
  }

  @Override
  public UsersDTO save(UserDetail userDetail) throws Exception {
    log.debug("Request to save Users : {}", userDetail);
    String pwdEncode = passwordEncoder.encode(userDetail.getPassword());
    RoleName roleName = userDetail.getRoleName();
    Optional<Roles> optionalRolesDTO = rolesService.findByRoleName(roleName);
    Roles roles = optionalRolesDTO.orElseThrow(() -> new Exception("Role not found - " + roleName));
    Users user = new Users();
    user.setHashPassword(pwdEncode);
    user.setLogin(userDetail.getUsername());
    user.setFirstName(userDetail.getFirstName());
    user.setLastName(userDetail.getLastName());
    user.setActivated(userDetail.getActivated());
    user.setRole(roles);
    usersRepository.save(user);
    return usersMapper.toDto(user);
  }

  @Override
  public UserDetail update(UserDetail userDetail) throws Exception {
    log.debug("Request to update Users : {}", userDetail);
    if(userDetail.getId() == null) throw new Exception("User not found");
    Users user =
        usersRepository.findById(userDetail.getId()).orElseThrow(() -> new Exception("User not found - " + userDetail));
    RoleName roleName = userDetail.getRoleName();
    Optional<Roles> optionalRolesDTO = rolesService.findByRoleName(roleName);
    Roles roles = optionalRolesDTO.orElseThrow(() -> new Exception("Role not found - " + roleName));
    if(userDetail.getPassword() != null) {
      String pwdEncode = passwordEncoder.encode(userDetail.getPassword());
      user.setHashPassword(pwdEncode);
    }
    user.setLogin(userDetail.getUsername());
    user.setFirstName(userDetail.getFirstName());
    user.setLastName(userDetail.getLastName());
    user.setActivated(userDetail.getActivated());
    user.setRole(roles);
    usersRepository.save(user);
    return userDetail;
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserVM> findAll() {
    log.debug("Request to get all Users");
    Optional<String> optionalAuthority = currentRoleName.parallelStream().findFirst();
    if(!optionalAuthority.isPresent()) {
      return Collections.EMPTY_LIST;
    }
    List<RoleName> roles = new ArrayList<>();
    switch (optionalAuthority.get()) {
      case "ADMIN":
        roles.add(RoleName.MENTOR);
        roles.add(RoleName.STUDENT);
        break;
      case "MENTOR":
        roles.add(RoleName.STUDENT);
        break;
      case "STUDENT":
        roles.add(RoleName.MENTOR);
        break;
      default:
    }
    return usersRepository
      .findAllByRoleNameIn(roles)
      .stream()
      .map( u -> {
        UserVM userVM = new UserVM();
        userVM.setId(u.getId());
        userVM.setFirstName(u.getFirstName());
        userVM.setLastName(u.getLastName());
        return userVM;
      })
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserDetailVM> findOne(Long id) {
    log.debug("Request to get Users : {}", id);
    Optional<String> optionalAuthority = currentRoleName.parallelStream().findFirst();
    if(!optionalAuthority.isPresent()) {
      return Optional.empty();
    }
    List<RoleName> roles = new ArrayList<>();
    switch (optionalAuthority.get()) {
      case "ADMIN":
        roles.add(RoleName.MENTOR);
        roles.add(RoleName.STUDENT);
        break;
      case "MENTOR":
        roles.add(RoleName.STUDENT);
        break;
      case "STUDENT":
        roles.add(RoleName.MENTOR);
        break;
      default:
    }
    return usersRepository.findAllByIdAndRoleNameIn(id, roles)
        .parallelStream()
        .map(u -> {
          UserDetailVM userDetailVM = new UserDetailVM();
          userDetailVM.setId(u.getId());
          userDetailVM.setUsername(u.getLogin());
          userDetailVM.setFirstName(u.getFirstName());
          userDetailVM.setLastName(u.getLastName());
          userDetailVM.setActivated(u.getActivated());
          userDetailVM.setRoleName(u.getRole().getName().name());
          return userDetailVM;
        })
        .findFirst();
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Users : {}", id);
    usersRepository.deleteById(id);
  }

  @Override
  public String authenticate(String username, String password, Boolean isRemember) {
    List<Users> usersList = usersRepository.checkLoginUser(username);
    Optional<Users> userOptional = usersList.parallelStream()
        .filter(u -> passwordEncoder.matches(password, u.getHashPassword())).findFirst();
    if (userOptional.isPresent()) {
      //erase credentials
      SecurityContextHolder.clearContext();
      User springSecurityUser = createSpringSecurityUser(username, userOptional.get());
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, password, springSecurityUser.getAuthorities());
      authenticationToken.setDetails(springSecurityUser);

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      boolean rememberMe = isRemember.booleanValue();
      return tokenProvider.createToken(authenticationToken, rememberMe);
    }
    return null;
  }

  private User createSpringSecurityUser(String lowercaseLogin, Users user) {
    if (!user.getActivated()) {
      throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
    }
    GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(user.getRole().getName().toString());
    return new User(user.getLogin(),
        user.getHashPassword(),
        Arrays.asList(grantedAuthorities));
  }
}
