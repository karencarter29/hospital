package com.example.alexthbot.fab.database.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ServiceID {
    private String doctorId;
    private String doctor;
    private String idChat;
}
