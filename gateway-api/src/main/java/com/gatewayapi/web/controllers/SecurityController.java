package com.gatewayapi.web.controllers;

import com.gatewayapi.web.services.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SecurityController {

    SecurityService securityService;

    SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/get")
    public ResponseEntity<String> auth(@RequestBody Map<String, Object> userInfo) {
        return securityService.auth(userInfo);
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody Map<String, Object> userInfo) {
        return securityService.register(userInfo);
    }
}
