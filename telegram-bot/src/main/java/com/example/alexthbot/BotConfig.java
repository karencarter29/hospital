package com.example.alexthbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;

@Configuration
@Slf4j
public class BotConfig {
    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botUserName;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url("https://5b74-31-202-112-40.ngrok.io").build();
    }

    @Bean
    public MainTelegramBot clinicBot(SetWebhook setWebhook) throws TelegramApiException {
        MainTelegramBot clinicBot = new MainTelegramBot(setWebhook);
        clinicBot.setName(botUserName);
        clinicBot.setToken(botToken);
        DefaultWebhook defaultWebhook = new DefaultWebhook();
        defaultWebhook.setInternalUrl("http://localhost:80");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class, defaultWebhook);
        log.info("SetWebHook from ClinicBot bot {}", setWebhook);
        log.info("Bot userName: " + clinicBot.getBotUsername());
        telegramBotsApi.registerBot(clinicBot, setWebhook);
        return clinicBot;
    }

}
