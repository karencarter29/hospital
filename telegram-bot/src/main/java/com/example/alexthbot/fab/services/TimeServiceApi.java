package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class TimeServiceApi implements TimeForBook{
    @Value("${gateway.api.get-all-doctors}")
    private String urlTime;

    @Override
    public List<String> getTime() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<String>> timeEntity = new RestTemplate().exchange(urlTime, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (timeEntity.getStatusCode() == HttpStatus.OK) {
            log.info(timeEntity.getBody().toString());
            return timeEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }
}
