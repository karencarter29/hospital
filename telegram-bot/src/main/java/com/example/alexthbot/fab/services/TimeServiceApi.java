package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class TimeServiceApi implements TimeForBook{
    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceID serviceID;
    @Value("${gateway.host}/patient/doctors")
    private String urlTime;

    @Override
    public List<String> getTime() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<String>> timeEntity = new RestTemplate().exchange(urlTime, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (timeEntity.getStatusCode() == HttpStatus.OK) {
            log.info(timeEntity.getBody().toString());
            return timeEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }
}
