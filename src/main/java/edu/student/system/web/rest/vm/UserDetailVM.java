package edu.student.system.web.rest.vm;

import lombok.Data;

@Data
public class UserDetailVM {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Boolean activated;

    private String roleName;
}
