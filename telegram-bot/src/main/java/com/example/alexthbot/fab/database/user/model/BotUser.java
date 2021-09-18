package com.example.alexthbot.fab.database.user.model;

import com.example.alexthbot.fab.actions.router.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BotUser {
    private Long id;
    private String firstName;
    private String secondName;
    private Role role;
    private String username;
    private String password;
    private String command;

    public BotUser(String firstName, String secondName, Role role, String login, String password, String command) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
        this.username = login;
        this.password = password;
        this.command = command;
    }
}