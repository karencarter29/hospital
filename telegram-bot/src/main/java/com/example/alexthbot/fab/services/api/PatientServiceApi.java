package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotUser;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.services.api.entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientServiceApi implements PatientService {
    @Autowired
    private BotUserService botUserService;
    @Autowired
    private TokenService tokenService;

    @Value("gateway.post.user")
    private String urlPostUser;

    @Value("${gateway.api.check-login}")
    private String urlChekLogin;

    @Value("${gateway.api.user.get}")
    private String urlGet;


    //регистрация
    @Override
    public HttpHeaders postNewUser(BotUser botUser) {
        UserDto userDto = new UserDto();
        userDto.setPassword(botUser.getPassword());
        userDto.setFirstName(botUser.getFirstName());
        userDto.setSecondName(botUser.getSecondName());
        userDto.setUsername(botUser.getUsername());
        userDto.setRole(botUser.getRole().toString());
        HttpHeaders headers = new RestTemplate().postForEntity(urlPostUser, userDto, UserDto.class).getHeaders();
        tokenService.setToken(headers);
        return headers;

    }

    @Override
    public String getUserByLogin() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<BotUser> BotUserEntity = new RestTemplate().exchange(urlChekLogin, HttpMethod.GET, httpEntity, BotUser.class);
        if (BotUserEntity.getStatusCode() == HttpStatus.OK) {
            return BotUserEntity.getBody().getUsername();
        } else {
            throw new RuntimeException();
        }
    }


    //логин
    @Override
    public BotUser userGet() {
        HttpEntity<BotUser> httpEntity = new HttpEntity<>(new BotUser());
        ResponseEntity<BotUser> patientEntity = new RestTemplate().exchange(urlGet, HttpMethod.POST, httpEntity, BotUser.class);
        if (patientEntity.getStatusCode() == HttpStatus.OK) {
            return patientEntity.getBody();
        } else {
            throw new RuntimeException();
        }

    }
}
