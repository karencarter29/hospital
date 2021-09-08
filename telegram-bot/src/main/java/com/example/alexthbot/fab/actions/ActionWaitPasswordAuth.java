package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ActionWaitPasswordAuth extends Action {
    @Autowired
    CheckLogPass checkLogPass;
    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String passwordForAuth = update.getMessage().getText();
        checkLogPass.setPassword(passwordForAuth);
        checkLogPass.setId(update.getMessage().getChatId());
        botUserService.setCommand(id,ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Введите пароль:");
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.PASSWORD_AUTH;
    }
}
