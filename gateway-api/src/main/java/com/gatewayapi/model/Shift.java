package com.gatewayapi.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {
    final Long id = new Random().nextLong();
    Long doctorId;
    Procedure procedure;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate date;


}
