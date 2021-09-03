package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Doctor {

    public Doctor(UUID id, String name) {
        this.userId = id;
        this.name = name;
    }

    UUID userId;
    UUID specialityId;
    String phoneNumber;

    String name;
}
