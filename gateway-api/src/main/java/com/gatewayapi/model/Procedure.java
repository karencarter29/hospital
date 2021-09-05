package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Procedure {
    Long id;
    Long specialityId;
    String procedureName;
}
