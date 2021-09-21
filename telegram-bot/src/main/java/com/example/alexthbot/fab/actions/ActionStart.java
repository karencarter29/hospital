package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionStart extends Action {
    @Autowired
    private ServiceID serviceID;
    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        serviceID.setIdChat(id);
        sendMessage.setText("\uD83D\uDE4C Welcome to our bot \uD83D\uDE4B" + "\nSelect an action, registration or login.\uD83C\uDD94");
        sendMessage.setReplyMarkup(getKeyboard());
        botUserService.setCommand(id, ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION);
    }

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Login");
        keyboardRow.add("Registration");
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.START;
    }
}
