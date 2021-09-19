package com.example.clinic.controllers;

import com.example.clinic.dto.AppointmentForDoctor;
import com.example.clinic.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {
    private AppointmentService appointmentService;

    @GetMapping("/doctor")
    public List<AppointmentForDoctor> getAppointments() {
        return appointmentService.getAppointments();
    }
}
