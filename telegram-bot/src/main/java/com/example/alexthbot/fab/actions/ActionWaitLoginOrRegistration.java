package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class ActionWaitLoginOrRegistration extends Action {

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        if (text.equals("Login")) {
            botUserService.setCommand(id, ActionEnum.LOGIN_AUTH);
            sendMessage.setText("Type login:⌨");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else if (text.equals("Registration")) {
            botUserService.setCommand(id, ActionEnum.CHOOSE_FIRST_NAME);
            String firstName = update.getMessage().getFrom().getFirstName();
            botUserService.setLogin(id, firstName + "_Hospital");
            sendMessage.setText("Your login will be automatically generated, enter your name:\uD83D\uDE09");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else {
            sendMessage.setText("Click the Login or Register button!\uD83D\uDDB0");
        }
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION;
    }
}
