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
public class ActionWaitLoginOrRegistration extends Action {
    @Autowired
    private CheckLogPass checkLogPass;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        if (text.equals("Логин")) {
            botUserService.setCommand(id, ActionEnum.LOGIN_AUTH);
            sendMessage.setText("Введите логин:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else if (text.equals("Регистрация")) {
            botUserService.setCommand(id, ActionEnum.CHOOSE_FIRST_NAME);
            String firstName = update.getMessage().getFrom().getFirstName();
            botUserService.setLogin(id, firstName + "_Hospital");
            sendMessage.setText("Ваш логин будет автоматически сгенерирован, введите имя:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else {
            sendMessage.setText("Нажмите на кнопку Логин или Регистрация");

        }
    }


    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION;
    }
}
