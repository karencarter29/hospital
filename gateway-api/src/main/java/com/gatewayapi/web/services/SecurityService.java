package com.gatewayapi.web.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SecurityService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> register(Map<String, Object> userInformation) {
        String url = "url to register";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }

    public ResponseEntity<String> auth(Map<String, Object> userInformation) {
        String url = "url to auth";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }
}
