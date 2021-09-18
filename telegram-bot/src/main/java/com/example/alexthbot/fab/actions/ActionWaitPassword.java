package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.PatientService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ActionWaitPassword extends Action {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ServiceID serviceID;
    @Autowired
    private TokenService tokenService;

    @Override
    public void action(Update update, SendMessage sendMessage, String password, String id) {
        botUserService.setPassword(id, password);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
        botUserService.setRole(update.getMessage().getChatId(), Role.ROLE_PATIENT);

        //постим юзера
        patientService.postNewUser(botUserService.user(id));
        sendMessage.setReplyMarkup(keyboard());
        sendMessage.setText("Вы зарегистрированы как: \n Имя: " + botUserService.getFirstName(id) +
                ", \nФамилия: " + botUserService.getSecondName(id) +
                "\nЛогин: " + botUserService.getLogin(id) +
                "\n Теперь выберите нужного доктора: ");
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

    @Override
    public ActionEnum getKey() {
        return ActionEnum.REGISTRATION_WAITING_PASSWORD;
    }
}
