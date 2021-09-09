package com.example.alexthbot.fab.services.entities;

import com.example.alexthbot.fab.services.Speciality;
import lombok.*;

import java.util.UUID;

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


