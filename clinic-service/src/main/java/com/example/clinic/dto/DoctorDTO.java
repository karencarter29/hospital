package com.example.clinic.dto;

import com.example.clinic.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Speciality speciality;
}
