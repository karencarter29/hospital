package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {

    private static final String PATIENT_SERVICE_ADDRESS = "http://localhost:8082";
    private static final String CLINIC_SERVICE_ADDRESS = "http://localhost:8083";

    RestTemplate restTemplate;

    @Autowired
    public PatientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
    }

    public ResponseEntity<String> getShiftsByDoctor(String doctorId) {
        String url = CLINIC_SERVICE_ADDRESS + "/shift/" + doctorId;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> getDoctors() {
        String url = CLINIC_SERVICE_ADDRESS + "/doctor";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> createAppointment(String shiftId, String patientId) {
        String url = PATIENT_SERVICE_ADDRESS + "/appointment/" + shiftId + "/" + patientId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.postForEntity(url, httpEntity, String.class);
    }

    public ResponseEntity<String> getAppointments(String patientId) {
        String url = PATIENT_SERVICE_ADDRESS + "/appointment/" + patientId;
        return restTemplate.getForEntity(url, String.class);
    }
}
