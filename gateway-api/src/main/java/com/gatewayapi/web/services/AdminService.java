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
import java.util.UUID;

@Service
public class AdminService {

    private RestTemplate restTemplate = new RestTemplate();

    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();


    public ResponseEntity<?> createAppointment(Map<String, Object> appointmentInfo) {
        return patientService.createAppointment(appointmentInfo);
    }

    public ResponseEntity<?> createShift(Map<String, Object> shiftInfo) {
        return doctorService.createShift(shiftInfo);
    }

    public ResponseEntity<?> getAllShifts() {
        String url = "url to get all shifts";
        //restTemplate.getForObject(url, ResponseEntity.class);
        return ResponseEntity.ok().body(getShifts());
    }

    public ResponseEntity<?> getAllAppointments() {
        String url = "url to get all appointments";
        //restTemplate.getForObject(url, ResponseEntity.class);
        return ResponseEntity.ok().body(getAppointments());
    }

    private String getAppointments() {
        Appointment[] appointments = {
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.RESERVED),
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.IN_PROGRESS),
                new Appointment(UUID.randomUUID(), UUID.randomUUID(), Condition.AVAILABLE)
        };
        return convertObjectToJson(appointments);
    }

    private String getShifts() {
        Shift[] shifts = {
              new Shift(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
              new Shift(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())
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