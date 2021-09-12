package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class BotAppointmentServiceApi implements BotAppointmentService {
    @Value("${gateway.host}/patient/appointment")
    private String urlPostApp;


    @Value("${gateway.host}/patient/appointments")
    private String urlGetApps;

    @Override
    public String postAppointment(BotAppointment botAppointment) {
        RestTemplate restTemplate = new RestTemplate();
        String body = restTemplate.postForEntity(urlPostApp, botAppointment.getId(), String.class).getBody();
        log.info(botAppointment.toString());
        return body;
    }

    @Override
    public List<Appointment> getAppointments() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Appointment>> appResponse = new RestTemplate().exchange(urlGetApps, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (appResponse.getStatusCode() == HttpStatus.OK) {
            log.info((appResponse.getBody()).toString());
            return appResponse.getBody();
        } else {
            throw ApiGatewayException.appointments();
        }
    }
}
