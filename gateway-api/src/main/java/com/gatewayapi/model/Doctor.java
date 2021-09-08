package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Doctor {
    Long userId;
    Speciality specialityId;
    String phoneNumber;
}
