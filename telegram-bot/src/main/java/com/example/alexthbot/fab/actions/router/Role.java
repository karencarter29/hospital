package com.example.alexthbot.fab.actions.router;

import com.example.alexthbot.fab.exeptions.RoleNotFoundException;

import java.util.Locale;

public enum Role {


    ROLE_PATIENT("PATIENT"),
    ROLE_DOCTOR("DOCTOR"),
    ROLE_ADMIN("ADMIN");
    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role interpret(String value) {
        if (value == null || value.isEmpty()) {
            throw new RoleNotFoundException(value);
        }
        value = value.trim().toLowerCase(Locale.ROOT);


        if (ROLE_PATIENT.value.equals(value)) return ROLE_PATIENT;
        if (ROLE_DOCTOR.value.equals(value)) return ROLE_DOCTOR;
        if (ROLE_ADMIN.value.equals(value)) return ROLE_ADMIN;
        throw new RoleNotFoundException(value);
    }
}
