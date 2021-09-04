package com.example.clinic.DTO;

import com.example.clinic.Model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
    private int id;
    private String procedureName;
    private Speciality speciality;


}
