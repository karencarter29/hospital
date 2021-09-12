package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@Data
public class Doctor {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Speciality speciality;
}
