package com.example.alexthbot.fab.services.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String role;


}
