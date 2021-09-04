package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {

    public Doctor(Long id, String name) {
        this.userId = id;
        this.name = name;
    }

    Long userId;
    Long specialityId;
    String phoneNumber;

    String name;
}
