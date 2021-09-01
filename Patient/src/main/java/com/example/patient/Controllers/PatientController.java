package com.example.patient.Controllers;


import com.example.patient.DTO.PatientDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Model.Patient;
import com.example.patient.Services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping
    public List<PatientDTO> getPatients() {
        return patientService.getPatient();
    }
    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient, @RequestBody Appointment appointment) {
        return patientService.updatePatient(patient, appointment);
    }
    @DeleteMapping
    public void deleletePatient(int patientId) {
        patientService.deletePatient(patientId);
    }
}
