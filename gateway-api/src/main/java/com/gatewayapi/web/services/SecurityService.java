package com.gatewayapi.web.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class SecurityService {

    private final String ADDRESS = "http://localhost:8077";

    private RestTemplate restTemplate;

    @Autowired
    public SecurityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> register(Map<String, Object> userInformation) {
        String url = ADDRESS + "/hospital/auth/register";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }

    public ResponseEntity<String> auth(Map<String, Object> userInformation) {
        String url = ADDRESS + "/hospital/auth/login";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }
}
