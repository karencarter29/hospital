package com.example.clinic.dto;

import com.example.clinic.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
    private UUID id;
    private String procedureName;
    private Speciality speciality;


}
