package com.example.patient.Controllers;

import com.example.patient.DTO.AppointmentDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    public Appointment saveAppointment(@PathVariable int shiftId, @PathVariable  int patientId){
        return appointmentService.saveAppointment(shiftId, patientId);
    }

    @PutMapping("/{shiftId}/{patientId}")
    public Appointment updateAppointment(@PathVariable int shiftId, @PathVariable int patientId) {
        return appointmentService.updateAppointment(shiftId, patientId);
    }

    @DeleteMapping("delete/{shiftId}/{patientId}")
    public void deleteAppointment(@PathVariable int shiftId, @PathVariable int patientId) {
        appointmentService.deleteAppointment(shiftId, patientId);
    }
}