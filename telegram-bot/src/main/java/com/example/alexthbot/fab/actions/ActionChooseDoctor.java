package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.Procedure;
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
public class ActionChooseDoctor extends Action {
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
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Procedure[] procedures = gson.fromJson(String.valueOf(procedureService.getProceduresOfTooth()), Procedure[].class);
        Arrays.stream(procedures).forEach(prod1 -> keyboardRow.add(prod1.getProcedure()));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard keyboardDrags() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Procedure[] procedures = gson.fromJson(String.valueOf(procedureService.getProceduresOfDrags()), Procedure[].class);
        Arrays.stream(procedures).forEach(prod1 -> keyboardRow.add(prod1.getProcedure()));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard keyboardDrag() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Консультация (1час)");
        keyboardRow.add("Бросить курить");
        keyboardRow.add("Бросить пить");
        keyboardRow.add("Лечение игромании");

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
