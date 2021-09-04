package com.example.clinic.DTO;

import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Procedure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    private int id;
    private String specialityName;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();

}
