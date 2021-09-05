package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.model.BotUser;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "true")
public class PatientServiceApi implements PatientService{
    @Autowired
    BotUserService botUserService;

    @Value("${gateway.api.create-user}")
    private String urlCreate;

    @Value("${gateway.api.check-login}")
    private String urlChekLogin;

    @Value("${gateway.host}/user/get")
    private String urlGetGet;



    @Override
    public Patient postNewUser() {
        BotUser botUser = botUserService.user(botUserService.getId().toString());
        RestTemplate restTemplate = new RestTemplate();
        Patient response = restTemplate.postForEntity(urlCreate, botUser, Patient.class).getBody();
        return response;

    }

    @Override
    public String getUserByLogin() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Patient> doctorsEntity = new RestTemplate().exchange(urlChekLogin, HttpMethod.GET, httpEntity, Patient.class);
        if (doctorsEntity.getStatusCode() == HttpStatus.OK ) {
            return doctorsEntity.getBody().getUsername();
        } else {
            throw new RuntimeException();
        }
    }



    @Override
    public Patient userGet() {
        HttpEntity<Patient> httpEntity = new HttpEntity<>(new Patient());

        ResponseEntity<Patient> patientEntity = new RestTemplate().exchange(urlCreate,HttpMethod.POST,httpEntity,Patient.class);
        if (patientEntity.getStatusCode() == HttpStatus.OK) {
            return patientEntity.getBody();
        } else {
            throw new RuntimeException();
        }

    }
}
