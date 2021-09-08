package com.gatewayapi.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AdminService {

    private RestTemplate restTemplate;

    private DoctorService doctorService;
    private PatientService patientService;

    public AdminService() {}

    @Autowired
    public AdminService(RestTemplate restTemplate, DoctorService doctorService, PatientService patientService) {
        this.restTemplate = restTemplate;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }


    public ResponseEntity<Object> createAppointment(Map<String, Object> appointmentInfo) {
        return patientService.createAppointment(appointmentInfo);
    }

    public ResponseEntity<Object> createShift(Map<String, Object> shiftInfo) {
        return doctorService.createShift(shiftInfo);
    }

    public ResponseEntity<Object> getAllShifts() {
        String url = "url to get all shifts";
//        restTemplate.getForObject(url, ResponseEntity.class);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> getAllAppointments() {
        String url = "url to get all appointments";
        //restTemplate.getForObject(url, ResponseEntity.class);
        return ResponseEntity.ok().build();
    }

    private String convertObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch(JsonProcessingException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}