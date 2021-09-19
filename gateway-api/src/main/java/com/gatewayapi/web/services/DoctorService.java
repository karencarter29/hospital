package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class DoctorService {

    private static final String CLINIC_SERVICE_ADDRESS = "http://10.186.0.4:8083";
    private static final String PATIENT_SERVICE_ADDRESS = "http://10.186.0.4:8082";

    private final RestTemplate restTemplate;

    @Autowired
    public DoctorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
    }

    public ResponseEntity<String> createShift(Map<String, Object> payload) {
        String url = PATIENT_SERVICE_ADDRESS + "/shift";
        return restTemplate.postForEntity(url, payload, String.class);
    }

    public ResponseEntity<String> getShiftsByDoctor(String doctorId) {
        String url = CLINIC_SERVICE_ADDRESS + "/shift/" + doctorId;
        return restTemplate.getForEntity(url, String.class);
    }
}
