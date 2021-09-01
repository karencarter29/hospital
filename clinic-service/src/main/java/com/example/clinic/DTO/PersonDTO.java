package com.example.clinic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private int role_id;


}
