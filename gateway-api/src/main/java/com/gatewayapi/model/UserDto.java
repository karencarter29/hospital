package com.gatewayapi.model;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String role;

    public UserDto(UUID id, String username, String firstName, String secondName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public UserDto() {
    }
}

