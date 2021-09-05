package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BotAppointmentServiceApi implements BotAppointmentService{
    @Value("/post/app")
    private String urlPostApp;

    @Value("/get/apps")
    private String urlGetApps;

    @Override
    public BotAppointment PostAppointment(BotAppointment botAppointment) {
        RestTemplate restTemplate = new RestTemplate();
        BotAppointment response = restTemplate.postForEntity(urlPostApp, botAppointment, BotAppointment.class).getBody();
        return response;
    }

    @Override
    public List<String> GetAppointment() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<String>> appResponse = new RestTemplate().exchange(urlGetApps,HttpMethod.GET,httpEntity,CollectionParams.get());
        if (appResponse.getStatusCode() == HttpStatus.OK) {
            log.info(Objects.requireNonNull(appResponse.getBody()).toString());
            return  appResponse.getBody();
        }
        else{
            throw new RuntimeException();
        }
    }
}
