package com.example.alexthbot.fab.services.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procedure {
    String id;
    String specialityId;
    String procedureName;
}
