package com.example.clinic.DTO;

import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Hospital hospital;
    private Doctor doctor;
    private int roomNumber;


}
