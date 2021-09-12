package com.example.alexthbot.fab.exeptions;

public class DoctorNotFoundExeption extends RuntimeException {
    public DoctorNotFoundExeption() {
        super("Ошибка получения докторов.");
    }
}
