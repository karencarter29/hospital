package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.*;
import com.example.alexthbot.fab.services.entities.Shift;
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
public class ActionChooseDate extends Action {
    @Autowired
    BotAppointment botAppointment;
    @Autowired
    DoctorServiceApi doctorServiceApi;
    @Autowired
    ProcedureService procedureService;
    @Autowired
    ServiceID serviceID;

    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        botAppointment.setProcedure(text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_TIME);
        Gson gson = new Gson();
        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            if (shifts[i].getProcedure().getProcedureName().equals(text)) {
                botAppointment.setId(shifts[i].getId());
            }
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Выберите дату: ");
        sendMessage.setReplyMarkup(keyboard());
        try {
            absSender.execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
//        Shift[] shifts = gson.fromJson(String.valueOf(doctorServiceApi.get()), Shift[].class);
//        Arrays.stream(shifts).forEach(shift1 -> keyboardRow.add(shift1.getDate().toString()));
        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            System.out.println();
            keyboardRow.add(shifts[i].getDate());
        }

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_DATE;
    }
}
