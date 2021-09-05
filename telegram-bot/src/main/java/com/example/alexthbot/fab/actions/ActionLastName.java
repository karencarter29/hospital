package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.services.Doctor;
import com.example.alexthbot.fab.services.DoctorService;
import com.example.alexthbot.fab.services.PatientService;
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
public class ActionLastName extends Action {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private BotUserService botUserService;

    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String name = update.getMessage().getText();

        botUserService.setSecondName(id, name);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR);
        botUserService.setIdAndRole(update.getMessage().getChatId(), Role.PATIENT);
        patientService.postNewUser(botUserService.user(id));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Вы зарегистрированы как: \n Имя: " + botUserService.getFirstName(id) +
                ", \nФамилия: " + botUserService.getSecondName(id) +
                "\nЛогин: " + botUserService.getLogin(id) +
                "\n Теперь выберите нужного доктора: ");
        sendMessage.setReplyMarkup(keyboard());
        try {
            absSender.execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Doctor[] doctor = gson.fromJson(String.valueOf(doctorService.get()), Doctor[].class);
        Arrays.stream(doctor).forEach(doctor1 -> keyboardRow.add(doctor1.getName()));

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    //public ReplyKeyboard keyboard() {
//    KeyboardRow keyboardRow = new KeyboardRow();
//    keyboardRow.add("Зубной техник");
//    keyboardRow.add("Врач нарколог");
//
//    List<KeyboardRow> keyboardRows = new ArrayList<>();
//    keyboardRows.add(keyboardRow);
//
//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//    replyKeyboardMarkup.setKeyboard(keyboardRows);
//    replyKeyboardMarkup.setResizeKeyboard(true);
//    return replyKeyboardMarkup;
//}
    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_LAST_NAME;
    }


}
