package com.example.clinic.DTO;

import com.example.clinic.Model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private Long id;
    private String name;
    private String address;
    private List<Room> rooms = new ArrayList<>();


}
