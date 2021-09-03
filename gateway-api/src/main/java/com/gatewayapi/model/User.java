package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.secondName = lastName;
    }

    UUID id;
    String username;
    String firstName;
    String secondName;
    UUID hospitalId;
    UUID roleId;

}
