package edu.student.system.web.rest.vm;

import edu.student.system.service.dto.RolesDTO;
import lombok.Data;

@Data
public class UserVM {
    private Long id;

    private String firstName;

    private String lastName;
}
