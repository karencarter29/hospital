package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procedure {
    Long id;
    Long specialityId;
    String procedureName;
}
