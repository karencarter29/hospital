package com.example.alexthbot.fab.services;


import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.services.entities.Shift;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ProcedureServiceApi implements ProcedureService {
    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceID serviceID;

    @Override
    public List<Shift> getProceduresById(Long id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<Shift>> proceduresEntity = new RestTemplate().exchange("http://localhost:8762/patient/doctor/"+ id +"/shifts", HttpMethod.GET, httpEntity, CollectionParams.get());
        if (proceduresEntity.getStatusCode() == HttpStatus.OK) {
            log.info(proceduresEntity.getBody().toString());
            return proceduresEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
    }



}
