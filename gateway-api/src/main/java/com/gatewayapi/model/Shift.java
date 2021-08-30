package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {
    final UUID id = UUID.randomUUID();
    UUID doctorId;
    UUID procedureId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate date;
}
