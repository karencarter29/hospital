package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@Slf4j
public class DoctorService {

    @Value("${clinic-service}")
    private static String clinicServiceAddress;
    @Value("${patient-service}")
    private static String patientServiceAddress;

    private final RestTemplate restTemplate;

    @Autowired
    public DoctorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
    }

    public ResponseEntity<String> createShift(Map<String, Object> shiftInfo) {
        log.info("DoctorService#createShift(); shiftInfo: {}", shiftInfo);
        String url = patientServiceAddress + "/shift";
        return restTemplate.postForEntity(url, shiftInfo, String.class);
    }

    public ResponseEntity<String> getShiftsByDoctor(String doctorId) {
        log.info("DoctorService#getShiftsByDoctor(doctorId: {})", doctorId);
        String url = clinicServiceAddress + "/shift/" + doctorId;
        return restTemplate.getForEntity(url, String.class);
    }
}
