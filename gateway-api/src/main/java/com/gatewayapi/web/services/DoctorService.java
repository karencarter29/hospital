package com.gatewayapi.web.services;

import com.gatewayapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DoctorService {

    RestTemplate restTemplate;

    @Autowired
    public DoctorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> createShift(Map<String, Object> payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    public ResponseEntity<Object> getShifts() {
        return null;
    }

    public ResponseEntity<Object> getAppointments() {
        return ResponseEntity.ok().body(new Appointment[] {
                new Appointment( new Shift(1L, new Procedure(1L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()), 1L, Condition.RESERVED),
                new Appointment( new Shift(2L, new Procedure(1L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()), 2L, Condition.IN_PROGRESS),
                new Appointment( new Shift(3L, new Procedure(1L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()), 3L, Condition.AVAILABLE)
        });
    }
}
