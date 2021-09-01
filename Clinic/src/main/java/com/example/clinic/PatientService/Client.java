package com.example.clinic.PatientService;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

abstract class Client {
    private RestTemplate rest;
    private String serviceFullPath;

    private static String GATEWAY_PATH = "http://localhost:8081/services";

    Client( String servicePath) {
        this.rest = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        this.serviceFullPath = GATEWAY_PATH + servicePath;
    }

    protected <T extends Object> T get(String path, Class<T> type) {
        return rest.getForObject(serviceFullPath + path, type);
    }

    protected <T extends Object, E> T post(String path, E object,  Class<T> type) {
        return rest.postForObject(serviceFullPath + path, object, type);
    }
}
