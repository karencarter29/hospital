package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.services.AuthServiceApi;
import com.example.alexthbot.fab.services.Doctor;
import com.example.alexthbot.fab.services.DoctorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ActionWaitPasswordAuth extends Action {
    @Autowired
    CheckLogPass checkLogPass;
    @Autowired
    AuthServiceApi authServiceApi;
    @Autowired
    DoctorService doctorService;
    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String passwordForAuth = update.getMessage().getText();
        checkLogPass.setPassword(passwordForAuth);
        checkLogPass.setId(update.getMessage().getChatId());
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Выберите доктора");
        if (authServiceApi.CheckLoginAndPassword(checkLogPass).is2xxSuccessful()) {
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
            sendMessage.setReplyMarkup(keyboard());
        }
        else{
            botUserService.setCommand(id, ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION);
            sendMessage.setText("Такого логина или пароля не существует");
            sendMessage.setReplyMarkup(getKeyboard());
        }
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Doctor[] doctors = gson.fromJson(String.valueOf(doctorService.get()), Doctor[].class);
        Arrays.stream(doctors).forEach(doctor1 -> keyboardRow.add(doctor1.getSpecialityId().getSpecialityName()));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
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
        return ActionEnum.PASSWORD_AUTH;
    }
}

