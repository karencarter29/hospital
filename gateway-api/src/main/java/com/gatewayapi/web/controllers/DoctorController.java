package com.gatewayapi.web.controllers;

import com.gatewayapi.security.TokenParser;
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
    private final TokenParser tokenParser;

    @Autowired
    DoctorController(DoctorService doctorService, TokenParser tokenParser) {
        this.doctorService = doctorService;
        this.tokenParser = tokenParser;
    }

    @PostMapping(value = "/shift", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createShift(@RequestBody Map<String, Object> payload) {
        return doctorService.createShift(payload);
    }

    @GetMapping("/shifts")
    public ResponseEntity<String> getMyShifts(@RequestHeader("Authorization") String header) {
        String doctorId = tokenParser.getUserId(header);
        return doctorService.getShiftsByDoctor(doctorId);
    }
}
