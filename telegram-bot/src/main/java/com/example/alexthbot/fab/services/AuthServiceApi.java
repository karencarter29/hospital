package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthServiceApi {
    @Value("")
    String url;

    public HttpStatus CheckLoginAndPassword(CheckLogPass checkLogPass) {
        RestTemplate restTemplate = new RestTemplate();
        HttpStatus ok = HttpStatus.OK;
        HttpStatus noOk = HttpStatus.FORBIDDEN;
        ResponseEntity<HttpStatus> httpStatusResponseEntity = restTemplate.postForEntity(url, checkLogPass, HttpStatus.class);
        log.info(httpStatusResponseEntity.toString());
        return  ok;
//        return httpStatusResponseEntity.getStatusCode();
    }
}
