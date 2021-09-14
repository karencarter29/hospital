package com.hospital.security.rest;

import com.hospital.security.config.SecretConfig;
import com.hospital.security.dto.AuthenticationRequestDto;
import com.hospital.security.dto.UserDto;
import com.hospital.security.model.User;
import com.hospital.security.security.jwt.JwtTokenProvider;
import com.hospital.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/hospital/auth")
public class AuthenticationController {

    private final UserService userService;

    private final SecretConfig secretConfig;

    @Autowired
    public AuthenticationController(UserService userService, SecretConfig secretConfig) {
        this.userService = userService;
        this.secretConfig = secretConfig;
    }

    @PostMapping("/role")
    public void addRole(@RequestBody String name) {
        userService.addRole(name);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String token = userService.login(requestDto);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(secretConfig.getHeader(), token);

            return ResponseEntity.ok().headers(responseHeaders).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        String token = userService.getTokenForUser(userDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(secretConfig.getHeader(), token);
        return ResponseEntity.ok().headers(responseHeaders).build();
    }
}
