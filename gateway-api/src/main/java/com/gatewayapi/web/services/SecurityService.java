package com.gatewayapi.web.services;

import com.gatewayapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SecurityService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<Object> register(Map<String, Object> userInformation) {
        String url = "url to register";
//        restTemplate.postForEntity(url, userInformation, String.class)
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Object> auth(Map<String, Object> userInformation) {
        String url = "url to auth";
//        restTemplate.postForEntity(url, userInformation, String.class);
        return ResponseEntity.status(HttpStatus.OK).body(new User("John", "Evans"));
    }
}
