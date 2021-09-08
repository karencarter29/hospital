package com.example.patient.DTO;

import com.example.patient.Model.Patient;
import com.example.patient.Model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Shift shift;
    private Patient patient;
}