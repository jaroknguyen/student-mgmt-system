package edu.student.system.web.rest;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import edu.student.system.security.jwt.JWTFilter;
import edu.student.system.service.UsersService;
import edu.student.system.service.dto.LoginDTO;
import edu.student.system.service.dto.UsersDTO;
import edu.student.system.web.rest.vm.JWTToken;
import edu.student.system.web.rest.vm.UserDetail;
import edu.student.system.web.rest.vm.UserDetailVM;
import edu.student.system.web.rest.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for managing {@link edu.student.system.domain.Users}.
 */
@RestController
@RequestMapping("/api")
public class UsersResource {

  private final Logger log = LoggerFactory.getLogger(UsersResource.class);

  private final UsersService usersService;

  public UsersResource(
    UsersService usersService
  ) {
    this.usersService = usersService;
  }

  @PostMapping("/users")
  @PreAuthorize("hasAnyRole('ADMIN','MENTOR','STUDENT')")
  public ResponseEntity<List<UserVM>> getUsers() {
    List<UserVM> users = usersService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PostMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','MENTOR','STUDENT')")
  public ResponseEntity<UserDetailVM> getUserDetailById(@PathVariable("id") Long id) {
    Optional<UserDetailVM> userDetailVMOptional = usersService.findOne(id);
    return new ResponseEntity<>(userDetailVMOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUserById(@PathVariable("id") Long id) {
    usersService.delete(id);
  }

  @PostMapping("/user/create")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody UserDetail userDetail) throws Exception {
    UsersDTO usersDTO = usersService.save(userDetail);
    return new ResponseEntity<>(usersDTO, HttpStatus.OK);
  }

  @PostMapping("/user/edit")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDetail> updateUser(@Valid @RequestBody UserDetail userDetail) throws Exception {
    UserDetail userDetail1 = usersService.update(userDetail);
    return new ResponseEntity<>(userDetail1, HttpStatus.OK);
  }
}
