package com.example.clinic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private int id;
    private int doctorId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private int procedureId;
}
