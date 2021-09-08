package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotUser;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.database.user.service.TokenService;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

@Service
public class PatientServiceApi implements PatientService{
    @Autowired
    BotUserService botUserService;
    @Autowired
    TokenService tokenService;

    @Value("${gateway.host}/user/")
    private String urlPostUser;

    @Value("${gateway.host}/user/checkLogin")
    private String urlChekLogin;

    @Value("${gateway.host}/user/get")
    private String urlGet;


    //регистрация
    @Override
    public HttpHeaders postNewUser(BotUser botUser) {
        UserDto userDto = new UserDto();
        userDto.setPassword(botUser.getPassword());
        userDto.setFirstName(botUser.getFirstName());
        userDto.setSecondName(botUser.getSecondName());
        userDto.setUsername(botUser.getLogin());
        userDto.setRole(botUser.getRole().toString());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplate.postForEntity(urlPostUser, userDto, UserDto.class).getHeaders();
        tokenService.setToken(headers);
        return headers;

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


    //логин
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
