package com.example.patient.Controllers;

import com.example.patient.DTO.AppointmentDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Model.Patient;
import com.example.patient.Model.Shift;
import com.example.patient.Services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> getAppointments() {
        return appointmentService.appointments();
    }

    @PostMapping
    public Appointment saveAppointment(@RequestBody AppointmentDTO appointment) throws ParseException {
        return appointmentService.saveAppointment(appointment);
    }

    @PutMapping
    public Appointment updateAppointment(@RequestBody AppointmentDTO newApp) throws ParseException {
        return appointmentService.updateAppointment(newApp);
    }

    @DeleteMapping("delete/{shiftId}/{patientId}")
    public void deleteAppointment(@PathVariable int shiftId, @PathVariable int patientId) {
        appointmentService.deleteAppointment(shiftId, patientId);
    }
}