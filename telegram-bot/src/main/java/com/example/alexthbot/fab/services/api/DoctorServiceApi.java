package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import com.example.alexthbot.fab.services.api.entities.Doctor;
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
public class DoctorServiceApi implements DoctorService {
    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceID serviceID;
    @Value("${gateway.host}/patient/doctors")
    private String url;

    @Override
    public List<Doctor> get() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<Doctor>> doctorsEntity = new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (doctorsEntity.getStatusCode() == HttpStatus.OK) {
            log.info(doctorsEntity.getBody().toString());
            return doctorsEntity.getBody();
        } else {
            throw ApiGatewayException.doctors();
        }
    }

}

