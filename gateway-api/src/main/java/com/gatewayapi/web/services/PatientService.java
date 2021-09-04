package com.gatewayapi.web.services;

import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import com.gatewayapi.model.Shift;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class PatientService {

    RestTemplate restTemplate;

    public ResponseEntity<Object> getShiftsByDoctor(Long id) {
        return ResponseEntity.ok().body(new Shift[]{
                new Shift(id, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
                new Shift(id, 2L, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())});
    }

    public ResponseEntity<Object> createAppointment(Map<String, Object> appointmentInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentInfo);
    }

    public ResponseEntity<Object> getAppointments() {
        return ResponseEntity.ok().body(new Appointment[]{
                new Appointment(1L, 1L, Condition.IN_PROGRESS)});
    }
}
