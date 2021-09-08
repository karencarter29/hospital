package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotUser;
import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


public interface PatientService {
    public HttpHeaders postNewUser(BotUser botUser);
    public String getUserByLogin();
    public BotUser userGet();
}
