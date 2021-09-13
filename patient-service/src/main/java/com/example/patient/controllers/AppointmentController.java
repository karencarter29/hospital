package com.example.patient.controllers;

import com.example.patient.dto.AppointmentDTO;
import com.example.patient.dto.AppointmentForDoctor;
import com.example.patient.model.Appointment;
import com.example.patient.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    @GetMapping("/{patientId}")
    public List<AppointmentDTO> appointments(@PathVariable UUID patientId) {
        return appointmentService.appointments(patientId);
    }

    @GetMapping
    public List<AppointmentForDoctor> getAppointments() {
        return appointmentService.getAppointments();
    }

    @PostMapping("/{shiftId}/{patientId}")
    public Appointment saveAppointment(@PathVariable UUID shiftId, @PathVariable  UUID patientId){
        return appointmentService.saveAppointment(shiftId, patientId);
    }

    @PutMapping("/{shiftId}/{patientId}")
    public Appointment updateAppointment(@PathVariable UUID shiftId, @PathVariable UUID patientId) {
        return appointmentService.updateAppointment(shiftId, patientId);
    }

    @DeleteMapping("/{shiftId}/{patientId}")
    public void deleteAppointment(@PathVariable UUID shiftId, @PathVariable UUID patientId) {
        appointmentService.deleteAppointment(shiftId, patientId);
    }
}