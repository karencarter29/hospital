package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PatientService {

    private final String ADDRESS = "http://localhost:8082";

    RestTemplate restTemplate;

    @Autowired
    public PatientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
    }

    public ResponseEntity<Object> getShiftsByDoctor(String doctorId) {
        String url = ADDRESS + "url to get shifts by doctor id";
        return null;
    }

    public ResponseEntity<Object> createAppointment(Map<String, Object> appointmentInfo) {
        String url = ADDRESS + "/appointment/{shift_id}/{patient_id}";
        return null;
    }

    public ResponseEntity<Object> getAppointments(String patientId) {
        String url = ADDRESS + "url for get appointments by patient id";
        return null;
    }
}
