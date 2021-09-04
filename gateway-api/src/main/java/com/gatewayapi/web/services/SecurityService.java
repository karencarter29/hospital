package com.gatewayapi.web.services;

import com.gatewayapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SecurityService {

    private RestTemplate restTemplate;

    public ResponseEntity<Object> register(Map<String, Object> userInformation) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Object> auth(Map<String, Object> userInformation) {
        return ResponseEntity.status(HttpStatus.OK).body(new User("John", "Evans"));
    }
}
