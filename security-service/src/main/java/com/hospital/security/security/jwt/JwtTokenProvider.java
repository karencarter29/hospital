package com.hospital.security.security.jwt;

import com.hospital.security.config.SecretConfig;
import com.hospital.security.dto.UserDto;
import com.hospital.security.model.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    public String createToken(UserDto userDto, List<Role> roles) {
        Claims claims = Jwts.claims();
        claims.setSubject(userDto.getUsername());
        claims.put("roles", getRoleNames(roles));
        claims.put("id", userDto.getId());
        claims.put("firstName", userDto.getFirstName());
        claims.put("secondName", userDto.getSecondName());
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder().
                setClaims(claims).
                setIssuedAt(now).
                setExpiration(exp).
                signWith(SignatureAlgorithm.HS256, secret).
                compact();
    }

    private List<String> getRoleNames(List<Role> roles) {
        List<String> result = new ArrayList<>();
        roles.forEach(role -> result.add(role.getName()));
        return result;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
}
