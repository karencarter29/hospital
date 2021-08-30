package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Procedure {
    UUID id;
    UUID specialityId;
    String procedureName;
}
