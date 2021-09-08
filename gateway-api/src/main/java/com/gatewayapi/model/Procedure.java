package com.gatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class Procedure {
    final Long id = new Random().nextLong();
    Long specialityId;
    String procedureName;
}
