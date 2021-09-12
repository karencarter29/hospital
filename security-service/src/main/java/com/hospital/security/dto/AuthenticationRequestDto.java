package com.hospital.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
