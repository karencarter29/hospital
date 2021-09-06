package com.example.patient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDTO {
    private UUID id;
    private String specialityName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String procedureName;
}