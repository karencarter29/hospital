package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceApi {
    @Value("${gateway.host}/user/get")
    String url;

    public HttpStatus CheckLoginAndPassword(CheckLogPass checkLogPass) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<HttpStatus> httpStatusResponseEntity = restTemplate.postForEntity(url, checkLogPass, HttpStatus.class);
        //log.info(httpStatusResponseEntity.toString());
        return HttpStatus.BAD_REQUEST;
        //return httpStatusResponseEntity.getStatusCode();
    }
}
