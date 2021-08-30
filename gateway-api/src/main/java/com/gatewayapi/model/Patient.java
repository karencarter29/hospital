package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Patient {
    UUID userId;
    String gender;
    String phoneNumber;
    LocalDate birthday;
}
