package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Room {
    UUID id;
    UUID hospitalId;
    UUID doctorId;
    Long roomNumber;
}
