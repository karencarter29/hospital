package com.example.clinic.DTO;

import com.example.clinic.Model.Room;
import com.example.clinic.Model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private UUID id;
    private int userId;
    private String phoneNumber;
    private Speciality speciality;
}
