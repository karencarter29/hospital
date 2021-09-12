package com.gatewayapi.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class DoctorService {

    private static final String CLINIC_SERVICE_ADDRESS = "http://localhost:8083";
    private static final String PATIENT_SERVICE_ADDRESS = "http://localhost:8082";

    private final RestTemplate restTemplate;

    @Autowired
    public DoctorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createShift(Map<String, Object> payload) {
        String url = PATIENT_SERVICE_ADDRESS + "/shift";
        return restTemplate.postForEntity(url, payload, String.class);
    }

    public ResponseEntity<String> getShiftsByDoctor(String doctorId) {
        String url = CLINIC_SERVICE_ADDRESS + "/shift/" + doctorId;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> getAppointments(String doctorId) {
        return ResponseEntity.ok().build(); //todo: implement the method to get appointments by doctor
    }
}
