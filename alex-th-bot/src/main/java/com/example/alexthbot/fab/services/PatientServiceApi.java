package com.example.alexthbot.fab.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "true")
public class PatientServiceApi implements PatientService{

    @Value("${gateway.api.create-user}")
    private String urlCreate;

    @Value("${gateway.api.check-login}")
    private String urlChekLogin;


    @Override
    public Patient postNewUser() {
        HttpEntity<Patient> httpEntity = new HttpEntity<>(new Patient());

        ResponseEntity<Patient> patientEntity = new RestTemplate().exchange(urlCreate,HttpMethod.POST,httpEntity,Patient.class);
        if (patientEntity.getStatusCode() == HttpStatus.OK) {
            return patientEntity.getBody();
        } else {
            throw new RuntimeException();//создать свой кастомный
        }
    }

    @Override
    public String getUserByLogin() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Patient> doctorsEntity = new RestTemplate().exchange(urlChekLogin, HttpMethod.GET, httpEntity, Patient.class);
        if (doctorsEntity.getStatusCode() == HttpStatus.OK ) {
            return doctorsEntity.getBody().getUsername();
        } else {
            throw new RuntimeException();//создать свой кастомный
        }
    }
}
