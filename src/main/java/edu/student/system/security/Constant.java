package edu.student.system.security;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Constant {
    public static final String ROLE_PREFIX = "ROLE_";

    public static Collection<String> getRealRoleName() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().parallelStream()
            .map(GrantedAuthority::getAuthority)
            .map(r -> r.replaceFirst(ROLE_PREFIX,""))
            .collect(Collectors.toList());
    }
}
