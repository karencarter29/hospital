package com.example.alexthbot.fab.database.user.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
@Configuration
public class Messager {

    @Bean
    public SendMessage message(){
        return new SendMessage();
    }
}
