package com.example.patient.controllers;


import com.example.patient.dto.PatientDTO;
import com.example.patient.model.Patient;
import com.example.patient.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @PostMapping
    public Patient addPatient(@RequestBody PatientDTO patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping
    public List<PatientDTO> getPatients() {
        return patientService.getPatient();
    }
    @PutMapping
    public Patient updatePatient(@RequestBody PatientDTO patient) {
        return patientService.updatePatient(patient);
    }
    @DeleteMapping("/{patientId}")
    public void deleletePatient(@PathVariable UUID patientId) {
        patientService.deletePatient(patientId);
    }
}
