package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.utils.CollectionParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "true")
public class DoctorServiceApi implements DoctorService {

    @Value("${gateway.api.get-all-doctors}")
    private String url;

//    @Value("http://192.168.2.101:8762/doctors")
//    private String url;
    @Override
    public List<Doctor> getAllDoctors() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<List<Doctor>> doctorsEntity = new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (doctorsEntity.getStatusCode() == HttpStatus.OK) {
            return doctorsEntity.getBody();
        } else {
            throw new RuntimeException();//создать свой кастомный
        }
    }
}
