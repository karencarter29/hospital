package com.example.clinic.dto;

import com.example.clinic.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private UUID id;
    private String name;
    private String address;
    private List<Room> rooms = new ArrayList<>();
}
