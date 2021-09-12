package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionAny extends Action {

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        sendMessage.setText("Неизвестная операция");
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.ANY;
    }
}
