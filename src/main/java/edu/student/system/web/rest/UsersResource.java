package edu.student.system.web.rest;

import edu.student.system.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for managing {@link edu.student.system.domain.Users}.
 */
@RestController
@RequestMapping("/api")
public class UsersResource {

  private final Logger log = LoggerFactory.getLogger(UsersResource.class);

  private static final String ENTITY_NAME = "users";

  private final UsersService usersService;


  public UsersResource(
    UsersService usersService
  ) {
    this.usersService = usersService;
  }



}
