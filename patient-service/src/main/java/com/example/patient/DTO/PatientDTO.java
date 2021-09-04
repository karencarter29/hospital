package com.example.patient.DTO;

import com.example.patient.Model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private int id;
    private int userId;
    private String gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private List<Appointment> appointments = new ArrayList<>();
}