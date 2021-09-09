package com.example.alexthbot.fab.services.entities;

import com.example.alexthbot.fab.services.Speciality;
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


