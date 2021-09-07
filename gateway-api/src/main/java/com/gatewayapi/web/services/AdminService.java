package com.gatewayapi.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return ResponseEntity.ok().body(getShifts());
    }

    public ResponseEntity<Object> getAllAppointments() {
        String url = "url to get all appointments";
        //restTemplate.getForObject(url, ResponseEntity.class);
        return ResponseEntity.ok().body(getAppointments());
    }

    private String getAppointments() {
        Appointment[] appointments = {
                new Appointment(1L, 1L, Condition.RESERVED),
                new Appointment(2L, 2L, Condition.IN_PROGRESS),
                new Appointment(3L, 3L, Condition.AVAILABLE)
        };
        return convertObjectToJson(appointments);
    }

    private String getShifts() {
        Shift[] shifts = {
              new Shift(1L, new Procedure(1L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(2L, new Procedure(2L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(3L, new Procedure(3L, "Consultation"), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())
        };
        return convertObjectToJson(shifts);
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