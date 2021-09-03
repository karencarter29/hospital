package com.example.alexthbot.fab.actions.controllers;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/tg")
public class PatientController {
    @Autowired
    BotAppointment botAppointment;


    @GetMapping(value = "/newapp", produces = MediaType.APPLICATION_JSON)
    public BotAppointment getNewApp() {
        return botAppointment;
    }
}
