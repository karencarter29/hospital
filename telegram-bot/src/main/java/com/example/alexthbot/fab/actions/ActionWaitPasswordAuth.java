package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.AuthServiceApi;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.example.alexthbot.fab.services.api.entities.Shift;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    DoctorService doctorService;
    @Autowired
    ServiceID serviceID;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        try {
            checkLogPass.setPassword(text);
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN);
            HttpStatus httpStatus = authServiceApi.checkLoginAndPassword(checkLogPass);
            if (httpStatus.is2xxSuccessful()) {
                sendMessage.setText("Choose a doctor\uD83D\uDC47");
                botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
                sendMessage.setReplyMarkup(keyboard());
            }
        }
        catch (RuntimeException e) {
            sendMessage.setText("There is no such login or password\uD83D\uDC47");
            sendMessage.setReplyMarkup(getKeyboard());
            botUserService.setCommand(id, ActionEnum.START);
        }
    }

    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        List<Doctor> doctors = doctorService.getDoctors();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Doctor> doctorList = mapper.convertValue(doctors,new TypeReference<List<Doctor>>() {     }
        );
        doctorList.forEach(doctor1 ->   keyboardRow.add(doctor1.getSpeciality().getSpecialityName()));
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

