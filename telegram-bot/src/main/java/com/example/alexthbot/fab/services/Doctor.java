package com.example.alexthbot.fab.services;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Doctor {
    Long userId;
    Speciality specialityId;
    String phoneNumber;
}


