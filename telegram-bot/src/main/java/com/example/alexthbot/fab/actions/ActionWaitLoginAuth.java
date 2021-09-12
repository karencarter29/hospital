package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class ActionWaitLoginAuth extends Action {
    @Autowired
    private CheckLogPass checkLogPass;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        checkLogPass.setLogin(text);
        botUserService.setCommand(id, ActionEnum.PASSWORD_AUTH);
        sendMessage.setText("Введите пароль:");
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.LOGIN_AUTH;
    }
}
