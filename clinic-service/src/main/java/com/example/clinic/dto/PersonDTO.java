package com.example.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private UUID id;
    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private int roleId;


}
