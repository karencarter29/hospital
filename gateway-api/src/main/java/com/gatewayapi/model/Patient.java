package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Patient {
    Long userId;
    String gender;
    String phoneNumber;
    LocalDate birthday;
}
