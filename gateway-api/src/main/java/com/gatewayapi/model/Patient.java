package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Patient {
    String id;
    String firstName;
    String lastName;
    String gender;
    String phoneNumber;
    LocalDate birthday;
}
