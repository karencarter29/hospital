package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotAppointment;

import java.util.List;

public interface BotAppointmentService {
    public String PostAppointment(BotAppointment botAppointment);
    public List<Appointment> GetAppointments();
}
