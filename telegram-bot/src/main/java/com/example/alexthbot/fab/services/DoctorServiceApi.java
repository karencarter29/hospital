package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.log4j.Log4j;
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
public class DoctorServiceApi implements DoctorService {

    @Value("${gateway.api.get-all-doctors}")
    private String url;

    @Override
    public List<Doctor> get() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Doctor>> doctorsEntity = new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (doctorsEntity.getStatusCode() == HttpStatus.OK) {
            log.info(doctorsEntity.getBody().toString());
            return doctorsEntity.getBody();
        } else {
            throw new RuntimeException();//создадим свой кастомный
        }
        }

}

