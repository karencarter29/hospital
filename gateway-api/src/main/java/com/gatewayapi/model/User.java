package com.gatewayapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.secondName = lastName;
    }

    Long id;
    String username;
    String firstName;
    String secondName;
    Long hospitalId;
    Long roleId;
}
