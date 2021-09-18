package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class TimeServiceApi implements TimeForBook {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ServiceID serviceID;
    @Value("${gateway.get.time}")
    private String urlTime;

    @Override
    public List<String> getTime() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<String>> timeEntity = new RestTemplate().exchange(urlTime, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (timeEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Method getTime: {}",timeEntity.getBody());
            return timeEntity.getBody();
        } else {
            throw ApiGatewayException.times();
        }
    }
}
