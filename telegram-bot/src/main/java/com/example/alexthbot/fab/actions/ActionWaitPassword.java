package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
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
    private static final String ANSWER_TEMPLATE = "Hello %s : %s.";
    @Override
    public void action(Update update, SendMessage sendMessage, String password, String id) {
       try {
           botUserService.setPassword(id, password);
           botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
           botUserService.setRole(update.getMessage().getChatId(), Role.ROLE_PATIENT);
           //постим юзера
           patientService.postNewUser(botUserService.user(id));
           sendMessage.setReplyMarkup(keyboard());
           sendMessage.setText("You have been registered as:\uD83E\uDDB8 " +
                   "\nName:\uD83D\uDC49 " + botUserService.getFirstName(id) +
                   "\nSecond Name:\uD83D\uDC49 " + botUserService.getSecondName(id) +
                   "\nLogin:\uD83D\uDC49 " + botUserService.getLogin(id) +
                   "\nNow select the doctor you want:\uD83E\uDE7A ");
       }
       catch (RuntimeException e) {
           sendMessage.setText("Something going wrong \uD83D\uDC47");
           botUserService.setCommand(id, ActionEnum.START);
           sendMessage.setReplyMarkup(keyboardOk());
       }
    }

    @Override
    public ReplyKeyboard keyboard() {
        String.format(ANSWER_TEMPLATE,"Mike","Duglas");
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
    public ReplyKeyboardMarkup keyboardOk() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Next");
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
