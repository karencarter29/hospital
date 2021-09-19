package com.example.alexthbot.fab.services.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private List<Appointment> appointments = new ArrayList<>();
}