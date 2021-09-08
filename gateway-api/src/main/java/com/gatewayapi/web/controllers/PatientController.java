package com.gatewayapi.web.controllers;

import com.gatewayapi.security.TokenConfig;
import com.gatewayapi.web.services.PatientService;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;
    TokenConfig tokenConfig;

    @Autowired
    PatientController(PatientService patientService, TokenConfig tokenConfig) {
        this.patientService = patientService;
        this.tokenConfig = tokenConfig;
    }

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getDoctors() {
        return patientService.getDoctors();
    }

    @PostMapping(value = "/appointment/", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> createAppointment(@RequestBody Map<String, Object> appointmentInfo) {
        return patientService.createAppointment(appointmentInfo);
    }

    @GetMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getMyAppointments(@RequestHeader("Authorization") String header) {
        String id = getPatientIdFromToken(header);
        return patientService.getAppointments(id);
    }

    @GetMapping(value = "/doctor/{id}/shifts", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getDoctorShifts(@PathVariable(name = "id") Long id) {
        return patientService.getShiftsByDoctor(id);
    }

    private String getPatientIdFromToken(String header) {
        String token = header.replace(tokenConfig.getPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(tokenConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }
}
