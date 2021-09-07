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

    public Patient(Long id, String firstName, String secondName, Role role, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
        this.login = login;
        this.password = password;
    }
}
