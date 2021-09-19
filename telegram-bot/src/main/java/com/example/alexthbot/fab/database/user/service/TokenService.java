package com.example.alexthbot.fab.database.user.service;

import com.example.alexthbot.fab.database.user.model.ServiceID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    protected Map<String, HttpHeaders> cacheT = new HashMap<>();
    @Autowired
    private ServiceID serviceID;

    public void setToken(HttpHeaders token) {
        cacheT.put(serviceID.getIdChat(), token);
    }

    public HttpHeaders getToken(String id) {
        return cacheT.get(id);
    }

}
