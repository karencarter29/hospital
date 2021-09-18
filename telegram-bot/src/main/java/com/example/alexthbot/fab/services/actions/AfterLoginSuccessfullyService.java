package com.example.alexthbot.fab.services.actions;

import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AfterLoginSuccessfullyService {
    @Autowired
    private BotUserService botUserService;
    @Autowired
    private DoctorService doctorService;

    public void afterLogin(SendMessage sendMessage, String id) {
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
        sendMessage.setText("Choose the right doctor:");
        sendMessage.setReplyMarkup(keyboard());
    }

    private ReplyKeyboard keyboard() {
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
}
