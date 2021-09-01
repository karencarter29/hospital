package com.hospital.security.security.jwt;

import com.hospital.security.model.Role;
import com.hospital.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser createJwtUser(User user) {
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getSecondName(),
                map(new ArrayList<>(user.getRoles())));
    }

    private static List<GrantedAuthority> map(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
