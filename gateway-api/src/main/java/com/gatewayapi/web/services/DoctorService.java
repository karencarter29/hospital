package com.gatewayapi.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import com.gatewayapi.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class DoctorService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<Object> getDoctors() {
        String url = "url to get all doctors";
        return ResponseEntity.status(HttpStatus.OK).body(new Doctor[] {
                new Doctor(UUID.randomUUID(), "David"),
                new Doctor(UUID.randomUUID(), "Garry")
        });
    }

    public ResponseEntity<Object> createShift(Map<String, Object> payload) {
        String url = "url to create shift";
        //restTemplate.postForEntity(url, payload, String.class);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    public ResponseEntity<Object> getShifts() {
        return null;
    }

    public ResponseEntity<Object> getAppointments() {
        String url = "url to get appointments";
        //restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok().body(new Appointment[] {
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.RESERVED),
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.IN_PROGRESS),
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.AVAILABLE)
        });
    }

    private String convertMapToJson(Map<String, Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(map);
        } catch(JsonProcessingException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}
