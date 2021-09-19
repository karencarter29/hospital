package com.example.alexthbot.fab.services.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private UUID id;
    private UUID doctorId;
    private String specialityName;
    private String startTime;
    private String endTime;
    private String date;
    private String procedureName;
}
