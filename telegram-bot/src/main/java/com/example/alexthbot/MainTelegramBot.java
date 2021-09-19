package com.example.alexthbot;

import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.ActionRouter;
import com.example.alexthbot.fab.database.user.model.BotUser;
import com.google.common.cache.Cache;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Component
@Setter
@Getter
public class MainTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    @Autowired
    private ActionRouter router;

    @Autowired
    private Cache<String, BotUser> cache;


    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            final Message message = update.getMessage();
            if (message.hasText()){

                String chatId = message.getChatId().toString();

                BotUser user = cache.getIfPresent(chatId);
                if (user == null){
                    user = new BotUser();
                    cache.put(chatId, user);
                }

                String command = user.getCommand();
                if (command == null){
                    command = message.getText();
                }
                log.info("Команда: " + command);


                final ActionEnum action = ActionEnum.interpret(command);
                router.get(action).action(update,this);
            }
        }
        if (update.hasCallbackQuery()){
        }
    }


}






