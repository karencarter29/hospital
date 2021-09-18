package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class BotAppointmentServiceApi implements BotAppointmentService {
    @Autowired
    private TokenService tokenService;

    @Value("${gateway.api.get-appoinments}")
    private String urlGetApps;

    @Override
    public HttpHeaders postAppointment(BotAppointment botAppointment) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplate.postForEntity("http://localhost:8762/patient/appointment/" + botAppointment.getId(), botAppointment.getId(), String.class).getHeaders();
        log.info("Answer from method postAppointment {}",botAppointment);
        tokenService.setToken(headers);
        return headers;
    }

    @Override
    public List<Appointment> getAppointments() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Appointment>> appResponse = new RestTemplate().exchange(urlGetApps, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (appResponse.getStatusCode() == HttpStatus.OK) {
            log.info("Answer from method getAppointments {}",appResponse.getBody());
            return appResponse.getBody();
        } else {
            throw ApiGatewayException.appointments();
        }
    }
}
