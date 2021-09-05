package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.actions.router.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String secondName;
    private Role role;
    private String login;
    private String password;
    private String command;


    public Patient(long l, String str0, String str1, String str2, Role patient) {
    }
}
