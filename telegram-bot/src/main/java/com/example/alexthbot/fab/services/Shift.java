package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    Long id;
    Long doctorId;
    Procedure procedure;
    String[] startTime;
    String[] endTime;
    String[] date;

    public Shift(Long doctorId, Procedure procedure, String[] startTime, String[] endTime, String[] date) {
        this.doctorId = doctorId;
        this.procedure = procedure;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }
}
