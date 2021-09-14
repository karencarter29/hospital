package com.example.alexthbot.fab.services.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private String id;
    private String doctorId;
    private String specialityName;
    private String startTime;
    private String endTime;
    private String date;
    private String procedureName;
}
