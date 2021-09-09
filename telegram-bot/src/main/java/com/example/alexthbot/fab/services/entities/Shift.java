package com.example.alexthbot.fab.services.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
