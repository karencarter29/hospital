package com.example.patient.dto;

import com.example.patient.model.Patient;
import com.example.patient.model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Shift shift;
    private Patient patient;
}