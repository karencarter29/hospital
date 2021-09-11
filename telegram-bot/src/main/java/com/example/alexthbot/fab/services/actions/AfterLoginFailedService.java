package com.example.alexthbot.fab.services.actions;

import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class AfterLoginFailedService {
    @Autowired
    private BotUserService botUserService;

    public void afterLogin(SendMessage sendMessage, String id) {
        botUserService.setCommand(id, ActionEnum.START);
        sendMessage.setText("Такого логина или пароля не существует, \n " +
                "выберите логин или регистрацию");
        sendMessage.setReplyMarkup(getKeyboard());
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
}
