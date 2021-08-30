package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Hospital {
    UUID id;
    String name;
    String address;
}
