package com.gatewayapi.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import com.gatewayapi.model.Shift;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AdminService {

    //todo: configure bean
    private RestTemplate restTemplate;

    private DoctorService doctorService;
    private PatientService patientService;

    public AdminService() {}

    public AdminService(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }


    public ResponseEntity<Object> createAppointment(Map<String, Object> appointmentInfo) {
        return patientService.createAppointment(appointmentInfo);
    }

    public ResponseEntity<Object> createShift(Map<String, Object> shiftInfo) {
        return doctorService.createShift(shiftInfo);
    }

    public ResponseEntity<Object> getAllShifts() {
        String url = "url to get all shifts";
        //restTemplate.getForObject(url, ResponseEntity.class);
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
              new Shift(1L, 1L, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(2L, 2L, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(3L, 3L, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())
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