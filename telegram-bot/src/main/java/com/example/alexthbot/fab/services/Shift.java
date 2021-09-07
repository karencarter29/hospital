package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private Long id;
    private Long DoctorId;
    private Long procedureId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
}
