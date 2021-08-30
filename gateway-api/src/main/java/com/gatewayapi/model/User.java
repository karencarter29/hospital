package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    UUID id;
    String username;
    String firstName;
    String secondName;
    UUID hospitalId;
    UUID roleId;

}
