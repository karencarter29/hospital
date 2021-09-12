package com.gatewayapi.web.controllers;

import com.gatewayapi.security.TokenParser;
import com.gatewayapi.web.services.PatientService;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final TokenParser tokenParser;

    @Autowired
    PatientController(PatientService patientService, TokenParser tokenParser) {
        this.patientService = patientService;
        this.tokenParser = tokenParser;
    }

    @PostMapping(value = "/appointment/{shift_id}")
    public ResponseEntity<String> createAppointment(@PathVariable(name = "shift_id") String shiftId,
                                                    @RequestHeader("Authorization") String header) {
        String patientId = tokenParser.getUserId(header);
        return patientService.createAppointment(shiftId, patientId);
    }

    @GetMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getMyAppointments(@RequestHeader("Authorization") String header) {
        String id = tokenParser.getUserId(header);
        return patientService.getAppointments(id);
    }

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getDoctors() {
        return patientService.getDoctors();
    }

    @GetMapping(value = "/doctor/{id}/shifts", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getDoctorShifts(@PathVariable(name = "id") String id) {
        return patientService.getShiftsByDoctor(id);
    }
}
