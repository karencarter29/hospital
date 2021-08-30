package com.gatewayapi.web.controllers;

import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/gateway")
public class GatewayApiController {

    @GetMapping("/get")
    public Appointment getAppointment() {
        return new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.IN_PROGRESS);
    }
}