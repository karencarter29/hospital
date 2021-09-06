package com.gatewayapi.model;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String role;

    public UserDto(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
