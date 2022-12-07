package edu.student.system.web.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.HashMap;

import edu.student.system.security.jwt.JWTFilter;
import edu.student.system.security.jwt.TokenProvider;
import edu.student.system.service.UsersService;
import edu.student.system.service.dto.LoginDTO;
import edu.student.system.web.rest.vm.JWTToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginResource {

    private final String tokenKey = "token";
    private final UsersService usersService;

    public LoginResource(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<HashMap<String, String>> authorize(@Valid @RequestBody LoginDTO loginDTO) {

        String jwt = usersService.authenticate(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getIsRemember());

        HashMap<String, String> tokenObject = new HashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if(jwt != null) {
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            status = HttpStatus.OK;
            tokenObject.put(tokenKey, jwt);
        }

        return new ResponseEntity<>(tokenObject, httpHeaders, status);
    }

    @PostMapping("/logout")
    public void authorize(HttpServletRequest httpServletRequest)
        throws ServletException {

        httpServletRequest.logout();

    }
}
