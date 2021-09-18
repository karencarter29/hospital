package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.services.api.AuthServiceApi;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ActionWaitPasswordAuth extends Action {
    @Autowired
    private CheckLogPass checkLogPass;
    @Autowired
    private AuthServiceApi authServiceApi;
    @Autowired
    private DoctorService doctorService;
    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        checkLogPass.setPassword(text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN);
        HttpStatus httpStatus = authServiceApi.checkLoginAndPassword(checkLogPass);
        if (httpStatus.is2xxSuccessful()) {
            sendMessage.setText("Choose a doctor");
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
            sendMessage.setReplyMarkup(keyboard());
        } else {
            botUserService.setCommand(id, ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION);
            sendMessage.setText("There is no such login or password");
            sendMessage.setReplyMarkup(getKeyboard());
        }
    }

    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Doctor[] doctors = gson.fromJson(String.valueOf(doctorService.getDoctors()), Doctor[].class);
        Arrays.stream(doctors).forEach(doctor1 -> keyboardRow.add(doctor1.getSpeciality().getSpecialityName()));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
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
        return ActionEnum.PASSWORD_AUTH;
    }
}

