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
public class ProcedureServiceApi implements ProcedureService {

    @Value("http://192.168.0.118:8762/patient/doctor/1/shifts")
    private String urlGetProcedureOfTooth;
    @Value("http://192.168.0.118:8762/patient/doctor/2/shifts")
    private String urlGetProcedureOfFrags;

    @Override
    public List<Procedure> getProceduresOfTooth() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Procedure>> proceduresEntity = new RestTemplate().exchange(urlGetProcedureOfTooth, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (proceduresEntity.getStatusCode() == HttpStatus.OK) {
            log.info(proceduresEntity.getBody().toString());
            return proceduresEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }

    @Override
    public List<Procedure> getProceduresOfDrags() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Procedure>> proceduresEntity = new RestTemplate().exchange(urlGetProcedureOfFrags, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (proceduresEntity.getStatusCode() == HttpStatus.OK) {
            log.info(proceduresEntity.getBody().toString());
            return proceduresEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }
}
