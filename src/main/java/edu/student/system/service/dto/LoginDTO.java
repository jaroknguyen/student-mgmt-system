package edu.student.system.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    private Boolean isRemember;

    @Override
    public String toString() {
        return "LoginDTO{" +
            "username='" + username + '\'' +
            ",isRemember='" + isRemember + '\'' +
            '}';
    }
}
