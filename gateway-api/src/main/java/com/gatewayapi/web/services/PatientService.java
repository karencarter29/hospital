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

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> getShiftsByDoctor(String id) {
        String url = "url to get shifts by doctor";
        //restTemplate.getForObject(url, ResponseEntity.class)
        return ResponseEntity.ok().body(new Shift[]{
                new Shift(UUID.fromString(id), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
                new Shift(UUID.fromString(id), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())});
    }

    public ResponseEntity<?> createAppointment(Map<String, Object> appointmentInfo) {
        String url = "url to create appointment";
        //restTemplate.postForEntity(url, appointmentInfo, String.class);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    public ResponseEntity<?> getAppointments() {
        String url = "url to get appointments by patient";
        //restTemplate.getForObject(url, ResponseEntity.class)
        return ResponseEntity.ok().body(new Appointment[]{
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.IN_PROGRESS)});
    }
}