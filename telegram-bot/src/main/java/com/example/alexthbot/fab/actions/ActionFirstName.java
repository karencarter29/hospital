package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionFirstName extends Action {

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        botUserService.setFirstName(id, text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_LAST_NAME);
        sendMessage.setText("Type your surname:\uD83D\uDC47");
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_FIRST_NAME;
    }
}
