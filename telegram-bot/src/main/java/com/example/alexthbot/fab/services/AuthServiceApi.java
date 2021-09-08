package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.database.user.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthServiceApi {
    @Autowired
    TokenService tokenService;
    @Value("${gateway.host}/user/get")
    String url;

    public HttpStatus CheckLoginAndPassword(CheckLogPass checkLogPass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HttpStatus> httpStatusResponseEntity = restTemplate.postForEntity(url, checkLogPass, HttpStatus.class);
        //токен после логина
        tokenService.setToken(httpStatusResponseEntity.getHeaders());
        log.info(httpStatusResponseEntity.toString());
        return httpStatusResponseEntity.getStatusCode();
    }
}
