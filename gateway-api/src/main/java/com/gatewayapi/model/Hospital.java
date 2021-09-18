package com.gatewayapi.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    private UUID id;
    private String name;
    private String address;
    private List<Room> rooms = new ArrayList<>();
}
