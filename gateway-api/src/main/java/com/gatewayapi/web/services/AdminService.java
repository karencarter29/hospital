package com.gatewayapi.web.services;

import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    private final RestTemplate restTemplate;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public AdminService(RestTemplateBuilder restTemplateBuilder,
                        DoctorService doctorService,
                        PatientService patientService) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

}