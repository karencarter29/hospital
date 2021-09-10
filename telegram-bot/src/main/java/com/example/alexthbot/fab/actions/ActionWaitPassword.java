package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.services.entities.Doctor;
import com.example.alexthbot.fab.services.DoctorService;
import com.example.alexthbot.fab.services.PatientService;
import com.example.alexthbot.fab.database.user.model.ServiceID;
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
public class ActionWaitPassword extends Action {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    ServiceID serviceID;
    @Autowired
    TokenService tokenService;
    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String password = update.getMessage().getText();
        botUserService.setPassword(id, password);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
        botUserService.setRole(update.getMessage().getChatId(), Role.ROLE_PATIENT);
        serviceID.setIdChat(id);

        //постим юзера
        patientService.postNewUser(botUserService.user(id));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(keyboard());
        sendMessage.setText("Вы зарегистрированы как: \n Имя: " + botUserService.getFirstName(id) +
                ", \nФамилия: " + botUserService.getSecondName(id) +
                "\nЛогин: " + botUserService.getLogin(id) +
                "\n Теперь выберите нужного доктора: ");
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

    @Override
    public ActionEnum getKey() {
        return ActionEnum.REGISTRATION_WAITING_PASSWORD;
    }
}
