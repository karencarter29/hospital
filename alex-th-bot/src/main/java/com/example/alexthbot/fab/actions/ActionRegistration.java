package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ActionRegistration extends Action {

    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Напишите логин:");
        sendMessage.setReplyMarkup(keyboard());
        botUserService.setCommand(id,ActionEnum.REGISTRATION_WAITING_LOGIN);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.REGISTRATION;
    }


}
