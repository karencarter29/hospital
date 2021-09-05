package com.gatewayapi.web.controllers;

import com.gatewayapi.web.services.PatientService;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;

    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/appointment", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> createAppointment(@RequestBody Map<String, Object> appointmentInfo) {
        return patientService.createAppointment(appointmentInfo);
    }

    @GetMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getMyAppointments() {
        return patientService.getAppointments();
    }

    @GetMapping(value = "/doctor/{id}/shifts", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getDoctorShifts(@PathVariable(name = "id") String id) {
        return patientService.getShiftsByDoctor(id);
    }
}
