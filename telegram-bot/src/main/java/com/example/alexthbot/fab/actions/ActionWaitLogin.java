package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class ActionWaitLogin extends Action {

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        botUserService.setCommand(id, ActionEnum.REGISTRATION_WAITING_PASSWORD);
        sendMessage.setText("Type password:⌨");
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.REGISTRATION_WAITING_LOGIN;
    }
}
