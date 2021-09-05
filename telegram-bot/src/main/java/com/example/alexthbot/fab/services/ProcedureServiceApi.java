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
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "true")
public class ProcedureServiceApi implements ProcedureService {

    @Value("${gateway.api.get-all-doctors}")
    private String urlGetProcedure;

    @Override
    public List<Procedure> getProcedures() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Procedure>> proceduresEntity = new RestTemplate().exchange(urlGetProcedure, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (proceduresEntity.getStatusCode() == HttpStatus.OK) {
            log.info(proceduresEntity.getBody().toString());
            return proceduresEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }
}
