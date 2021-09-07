package com.gatewayapi.web.controllers;

import com.gatewayapi.web.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/shift", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> createShift(@RequestBody Map<String, Object> payload) {
        return doctorService.createShift(payload);
    }

    @GetMapping("/shifts")
    public ResponseEntity<Object> getMyShifts() {
        return doctorService.getShifts();
    }

    @GetMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getPatientsAppointments() {
        return doctorService.getAppointments();
    }
}
