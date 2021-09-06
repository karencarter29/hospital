package com.gatewayapi.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/gateway")
public class GatewayApiController {

    private final String ADDRESS = "http://localhost:8763/clinic";

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/hospital")
    public ResponseEntity<Object> createHospital(@RequestBody Map<String, Object> payload) {
        String url = ADDRESS + "/hospital";
        return restTemplate.postForEntity(url, payload, Object.class);
    }
}