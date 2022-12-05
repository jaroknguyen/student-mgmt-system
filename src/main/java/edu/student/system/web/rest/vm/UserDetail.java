package edu.student.system.web.rest.vm;

import edu.student.system.domain.enumeration.RoleName;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDetail {

    private Long id;

    @NonNull
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean activated;

    @NonNull
    private RoleName roleName;
}
