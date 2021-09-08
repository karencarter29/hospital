package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Slf4j
@Service
public class DateServiceApi implements DateService {


    @Override
    public List<Shift> getData(Long id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Shift>> proceduresEntity = new RestTemplate().exchange("http://192.168.0.118:8762/patient/doctor/"+ id +"/shifts", HttpMethod.GET, httpEntity, CollectionParams.get());
        if (proceduresEntity.getStatusCode() == HttpStatus.OK) {
            log.info(proceduresEntity.getBody().toString());
            return proceduresEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }
}
