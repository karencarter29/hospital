package com.hospital.security.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
}
