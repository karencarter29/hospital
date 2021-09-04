package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Shift {
    private int id;
    private String specialityName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String procedureName;
}
