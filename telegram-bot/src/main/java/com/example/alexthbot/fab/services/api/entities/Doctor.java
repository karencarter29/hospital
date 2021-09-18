package com.example.alexthbot.fab.services.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Doctor {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Speciality speciality;
}


