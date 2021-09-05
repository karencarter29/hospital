package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.actions.router.Role;

public class PatientServiceMock implements PatientService{
    @Override
    public Patient postNewUser() {
        return null;
    }

    @Override
    public String getUserByLogin() {
        return "Geek";
    }

    @Override
    public Patient userGet() {
        return new Patient(4L,"Geek","Григорий","Афанасий", Role.PATIENT);
    }
}
