package com.gatewayapi.web.controllers;

import com.gatewayapi.web.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService = new AdminService();

    @GetMapping(value = "/shifts", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getAllShifts() {
        return adminService.getAllShifts();
    }

    @GetMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getAllAppointments() {
        return adminService.getAllAppointments();
    }

//    @PostMapping(value = "/appointment", consumes = MediaType.APPLICATION_JSON)
//    public ResponseEntity<String> createAppointment(@RequestBody Map<String, Object> appointmentInfo) {
//        return adminService.createAppointment(appointmentInfo);
//    }

    @PostMapping(value = "/shift", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createShift(@RequestBody Map<String, Object> shiftInfo) {
        return adminService.createShift(shiftInfo);
    }
}
