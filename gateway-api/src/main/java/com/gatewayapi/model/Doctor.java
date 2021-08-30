package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Doctor {
    UUID userId;
    UUID specialityId;
    String phoneNumber;
}
