package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.Doctor;
import com.example.alexthbot.fab.services.DoctorService;
import com.example.alexthbot.fab.services.ProcedureService;
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
public class ChooseDoctorAfterLogin extends Action {
    @Autowired
    DoctorService doctorService;

    @Autowired
    BotAppointment botAppointment;
    @Autowired
    ProcedureService procedureService;
    @Override
    public void action(Update update, AbsSender absSender) {
        SendMessage sendMessage = new SendMessage();
        String id = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        botAppointment.setDoctor(text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DATE);
        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(keyboard());
        sendMessage.setText("Выберите процедуру: \n(В первый раз советуем выбрать консультацию)");

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

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN;
    }
}
