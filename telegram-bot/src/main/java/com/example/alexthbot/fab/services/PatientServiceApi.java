package com.example.alexthbot.fab.services;

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
    private String urlPostUser;

    @Value("${gateway.api.check-login}")
    private String urlChekLogin;

    @Value("${gateway.host}/user/get")
    private String urlGet;



    @Override
    public BotUser postNewUser(BotUser botUser) {
        RestTemplate restTemplate = new RestTemplate();
        BotUser response = restTemplate.postForEntity(urlPostUser, botUser, BotUser.class).getBody();
        return response;

    }

    @Override
    public String getUserByLogin() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<BotUser> BotUserEntity = new RestTemplate().exchange(urlChekLogin, HttpMethod.GET, httpEntity, BotUser.class);
        if (BotUserEntity.getStatusCode() == HttpStatus.OK ) {
            return BotUserEntity.getBody().getLogin();
        } else {
            throw new RuntimeException();
        }
    }



    @Override
    public BotUser userGet() {
        HttpEntity<BotUser> httpEntity = new HttpEntity<>(new BotUser());
        ResponseEntity<BotUser> patientEntity = new RestTemplate().exchange(urlGet,HttpMethod.POST,httpEntity,BotUser.class);
        if (patientEntity.getStatusCode() == HttpStatus.OK) {
            return patientEntity.getBody();
        } else {
            throw new RuntimeException();
        }

    }
}
