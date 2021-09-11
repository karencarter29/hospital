package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotUser;
import org.springframework.http.HttpHeaders;


public interface PatientService {
    public HttpHeaders postNewUser(BotUser botUser);

    public String getUserByLogin();

    public BotUser userGet();
}
