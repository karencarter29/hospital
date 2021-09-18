package com.example.alexthbot.fab.services.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Speciality {
    private String id;
    private String specialityName;
    private Doctor [] doctors;
    private Procedure [] procedures;
}
