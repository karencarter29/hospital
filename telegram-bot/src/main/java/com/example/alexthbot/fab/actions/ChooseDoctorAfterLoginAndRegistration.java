package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ChooseDoctorAfterLoginAndRegistration extends Action {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    CheckLogPass checkLogPass;
    @Autowired
    private BotUserService botUserService;
    @Autowired
    PatientService patientService;
    @Autowired
    AuthServiceApi authServiceApi;

    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        if (authServiceApi.CheckLoginAndPassword(checkLogPass).is2xxSuccessful()) {
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
        sendMessage.setChatId(id);
        sendMessage.setText("Выберите нужного доктора:");
        sendMessage.setReplyMarkup(keyboard());
        try {
            absSender.execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }
        else {
            botUserService.setCommand(id,ActionEnum.START);
            sendMessage.setChatId(id);
            sendMessage.setText("Такого логина или пароля не существует, + \n " +
                    "выберите логин или регистрацию");
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

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN;
    }
}
