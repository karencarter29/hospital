package com.gatewayapi.web.services;

import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import com.gatewayapi.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DoctorService {

    RestTemplate restTemplate;

    @Autowired
    public DoctorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getDoctors() {
        return ResponseEntity.status(HttpStatus.OK).body(new Doctor[] {
                new Doctor(1L, "David"),
                new Doctor(2L, "Garry")
        });
    }

    public ResponseEntity<Object> createShift(Map<String, Object> payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    public ResponseEntity<Object> getShifts() {
        return null;
    }

    public ResponseEntity<Object> getAppointments() {
        return ResponseEntity.ok().body(new Appointment[] {
                new Appointment(1L, 1L, Condition.RESERVED),
                new Appointment(2L, 2L, Condition.IN_PROGRESS),
                new Appointment(3L, 3L, Condition.AVAILABLE)
        });
    }
}
