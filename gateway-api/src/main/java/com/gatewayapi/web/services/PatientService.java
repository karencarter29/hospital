package com.gatewayapi.web.services;

import com.gatewayapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PatientService {

    private final String ADDRESS = "http://localhost:8082";

    RestTemplate restTemplate;

    @Autowired
    public PatientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getDoctors() {
        return ResponseEntity.status(HttpStatus.OK).body(new Doctor[] {
                new Doctor(1L, new Speciality(1L, "Стоматолог"), "+380-97-246-325-45-65"),
                new Doctor(2L, new Speciality(2L, "Нарколог"), "+380-68-542-762-58-61")
        });
    }

    public ResponseEntity<Object> getShiftsByDoctor(Long id) {
        return ResponseEntity.ok().body(new Shift[]{
                new Shift(id, new Procedure(1L, "Consultation"),
                        LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()),
                new Shift(id, new Procedure(2L, "Consultation"),
                        LocalDateTime.now(), LocalDateTime.now(), LocalDate.now())});
    }

    public ResponseEntity<Object> createAppointment(Map<String, Object> appointmentInfo) {
        String url = ADDRESS + "/appointment/{shift_id}/{patient_id}";
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentInfo);
    }

    public ResponseEntity<Object> getAppointments(String id) {
        return ResponseEntity.ok().body(new Appointment[]{
                new Appointment(new Shift(1L, new Procedure(1L, "Consultation"),
                        LocalDateTime.now(), LocalDateTime.now(), LocalDate.now()), 1L, Condition.IN_PROGRESS)});
    }
}
