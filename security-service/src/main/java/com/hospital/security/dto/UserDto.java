package com.hospital.security.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
}
