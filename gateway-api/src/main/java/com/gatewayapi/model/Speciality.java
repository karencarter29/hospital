package com.gatewayapi.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Speciality {
    private UUID id;
    private String specialityName;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
}
