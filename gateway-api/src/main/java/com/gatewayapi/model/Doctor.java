package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {
    Long userId;
    Long specialityId;
    String phoneNumber;

    String name;
    public Doctor(Long id, String name) {
        this.name = name;
        userId = id;
    }
}
