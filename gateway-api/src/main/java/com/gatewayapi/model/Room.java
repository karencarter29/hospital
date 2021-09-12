package com.gatewayapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Hospital hospital;
    private Doctor doctor;
    private String roomNumber;
}
