package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Shift {
    private UUID id;
    private UUID doctorId;
    private String specialityName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String procedureName;
}
