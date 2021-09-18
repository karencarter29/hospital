package com.example.clinic.dto;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Hospital hospital;
    private Doctor doctor;
    private String roomNumber;


}
