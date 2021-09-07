package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Doctor {
    Long userId;
    Speciality specialityId;
    String phoneNumber;
}
