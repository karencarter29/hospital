package com.gatewayapi.model;

import lombok.*;

import java.util.Random;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Procedure {
    private UUID id;
    private String procedureName;
    private Speciality speciality;
}
