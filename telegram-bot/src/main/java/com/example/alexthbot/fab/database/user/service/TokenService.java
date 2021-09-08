package com.example.alexthbot.fab.database.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {

    protected Map<String, HttpHeaders> cacheT;
    @Autowired
    BotUserService botUserService;

    public void setToken(HttpHeaders token){
        cacheT.put(botUserService.getId().toString(),token);
    }

    public HttpHeaders getToken(String id){
        return cacheT.get(id);
    }

}
