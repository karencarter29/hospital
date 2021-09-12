package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionStart extends Action {

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        sendMessage.setText("Приветствую вас в нашем боте\nВыберите действие, регистрацию или логин.");
        sendMessage.setReplyMarkup(getKeyboard());
        botUserService.setCommand(id, ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION);
    }

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Логин");
        keyboardRow.add("Регистрация");
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
