package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotUser;
import org.springframework.stereotype.Service;


public interface PatientService {
    public Patient postNewUser(BotUser botUser);
    public String getUserByLogin();
    public Patient userGet();
}
