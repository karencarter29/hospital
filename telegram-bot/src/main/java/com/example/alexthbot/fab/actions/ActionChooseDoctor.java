package com.example.alexthbot.fab.actions;
import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
public class ActionChooseDoctor extends Action {
    @Autowired
    BotAppointment botAppointment;
    @Autowired
    ProcedureService procedureService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ServiceID serviceID;

    @Override
    public void action(Update update, AbsSender absSender) {
        SendMessage sendMessage = new SendMessage();
        String id = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        serviceID.setDoctor(text);
        Gson gson = new Gson();
        Doctor[] doctors = gson.fromJson(String.valueOf(doctorService.get()), Doctor[].class);
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getSpecialityId().getSpecialityName().equals(text)) {
                serviceID.setDoctorId(doctors[i].getUserId());
            }

        }


        botAppointment.setDoctor(text);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
        botUserService.setCommand(id, ActionEnum.CHOOSE_DATE);

        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(keyboardTooth());
        sendMessage.setText("Выберите процедуру: \n(В первый раз советуем выбрать консультацию)");

        try {
            absSender.execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public ReplyKeyboard keyboardTooth() {
        Gson gson = new Gson();
        KeyboardRow keyboardRow = new KeyboardRow();
        //List<Shift> shifts =  procedureService.getProceduresById(serviceID.getDoctorId());
//        for (int i = 0; i < shifts.size(); i++) {
//            keyboardRow.add(shifts.get(i).getProcedure().getProcedure());
//        }  ClassCastException
        //shifts.stream().forEach(prod1 -> keyboardRow.add(prod1.getProcedure().getProcedure()));

        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            keyboardRow.add(shifts[i].getProcedure().getProcedureName());
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
        return ActionEnum.CHOOSE_DOCTOR;
    }
}
