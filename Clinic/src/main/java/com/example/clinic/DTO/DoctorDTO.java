package com.example.clinic.DTO;

import com.example.clinic.Model.Room;
import com.example.clinic.Model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private int  id;
    private int userId;

    private String phoneNumber;
    private Speciality speciality;
    private Room room;


}
