package com.example.patient.Controllers;

import com.example.patient.DTO.AppointmentDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> getAppointments() {
        return appointmentService.appointments();
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