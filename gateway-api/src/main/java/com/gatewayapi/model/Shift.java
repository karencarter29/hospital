package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {
    Long id = new Random().nextLong();
    Long doctorId;
    Long procedureId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate date;

    public Shift(Long doctorId, Long procedureId, LocalDateTime startTime, LocalDateTime endTime, LocalDate date) {
        this.doctorId = doctorId;
        this.procedureId = procedureId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }
}
