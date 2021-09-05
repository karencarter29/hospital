package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {

    Long id;

    String username;
    String firstName;
    String secondName;
    Long hospitalId;
    Long roleId;
}
