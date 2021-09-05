package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {
    final Long id = new Random().nextLong();
    Long doctorId;
    Long procedureId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate date;
}
