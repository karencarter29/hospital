package com.gatewayapi.web.controllers;

import com.gatewayapi.web.services.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SecurityController {

    SecurityService securityService;

    SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> auth(@RequestBody Map<String, Object> userInfo) {
        return securityService.auth(userInfo);
    }

    @GetMapping("/")
    public ResponseEntity<Object> register(@RequestBody Map<String, Object> userInfo) {
        return securityService.register(userInfo);
    }
}
