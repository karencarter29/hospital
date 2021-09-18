package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Service
public class AuthServiceApi {
    @Autowired
    private TokenService tokenService;
    @Value("${gateway.api.user.get}")
    private String url;
    public HttpStatus checkLoginAndPassword(CheckLogPass checkLogPass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HttpStatus> httpStatusResponseEntity = restTemplate.postForEntity(url, checkLogPass, HttpStatus.class);
        if (httpStatusResponseEntity.getStatusCode() == HttpStatus.OK) {
            tokenService.setToken(httpStatusResponseEntity.getHeaders());
            log.info("Answer from method checkLoginAndPassword {}",httpStatusResponseEntity);
            return httpStatusResponseEntity.getStatusCode();
        } else {
            throw ApiGatewayException.login();
        }

    }

}
