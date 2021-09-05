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

                new Doctor(1L, "Зубной техник"),
                new Doctor(2L, "Врач невролог")

        });
    }

    public ResponseEntity<String> createShift(Map<String, Object> payload) {
        String url = "url to create shift";
        //restTemplate.postForEntity(url, payload, String.class);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    public ResponseEntity<?> getShifts() {
        return null;
    }

    public ResponseEntity<?> getAppointments() {
        String url = "url to get appointments";
        //restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok().body(new Appointment[] {
                new Appointment(1L, 1L, Condition.RESERVED),
                new Appointment(2L, 2L, Condition.IN_PROGRESS),
                new Appointment(3L, 3L, Condition.AVAILABLE)
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
