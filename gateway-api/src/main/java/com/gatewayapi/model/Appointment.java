package com.gatewayapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Shift shift;
    private Patient patient;
}

