package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PatientService {

    @Value("${patient-service}")
    private static String patientServiceAddress;
    @Value("${clinic-service}")
    private static String clinicServiceAddress;

    private final RestTemplate restTemplate;

    @Autowired
    public PatientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
    }

    public ResponseEntity<String> getShiftsByDoctor(String doctorId) {
        log.info("PatientService#getShiftsByDoctor(doctorId: {})", doctorId);
        String url = clinicServiceAddress + "/shift/" + doctorId;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> getDoctors() {
        log.info("PatientService#getDoctors()");
        String url = clinicServiceAddress + "/doctor";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> createAppointment(String shiftId, String patientId) {
        log.info("PatientService#createAppointment(shiftId: {}, patientId: {})", shiftId, patientId);
        String url = patientServiceAddress + "/appointment/" + shiftId + "/" + patientId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.postForEntity(url, httpEntity, String.class);
    }

    public ResponseEntity<String> getAppointments(String patientId) {
        log.info("PatientService#getAppointments(patientId: {})", patientId);
        String url = patientServiceAddress + "/appointment/" + patientId;
        return restTemplate.getForEntity(url, String.class);
    }
}
