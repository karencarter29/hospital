package com.example.patient.DTO;

import com.example.patient.Model.Patient;
import com.example.patient.Model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    @JsonIgnore
    private Shift shift;
    @JsonIgnore
    private Patient patient;
}