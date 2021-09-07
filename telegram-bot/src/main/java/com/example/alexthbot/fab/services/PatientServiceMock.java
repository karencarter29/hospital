package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.model.BotUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "false")
public class PatientServiceMock implements PatientService{


    @Override
    public Patient postNewUser(BotUser botUser) {
        return null;
    }

    @Override
    public String getUserByLogin() {
        return "Geek";
    }

    @Override
    public Patient userGet() {
        return new Patient(4L,"Григорий","Афанасий", Role.PATIENT,"Geek","qwerty");
    }
}
