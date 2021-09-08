package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ActionWaitLoginAuth extends Action {
    @Autowired
    CheckLogPass checkLogPass;
    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String loginForAuth = update.getMessage().getText();
        checkLogPass.setLogin(loginForAuth);
        botUserService.setCommand(id,ActionEnum.PASSWORD_AUTH);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Введите пароль:");
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    @Override
    public ActionEnum getKey() {
        return ActionEnum.LOGIN_AUTH;
    }
}
