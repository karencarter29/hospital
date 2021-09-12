package com.example.alexthbot.fab.services.api.entities;

import com.example.alexthbot.fab.services.api.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Doctor {
    String id;
    String firstName;
    String lastName;
    String phoneNumber;
    Speciality speciality;
}


