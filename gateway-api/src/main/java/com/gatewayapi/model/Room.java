package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    Long id;
    Long hospitalId;
    Long doctorId;
    Long roomNumber;
}
