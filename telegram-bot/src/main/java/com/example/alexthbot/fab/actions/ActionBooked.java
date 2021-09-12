package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.api.BotAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionBooked extends Action {
    @Autowired
    private BotAppointment botAppointment;

    @Autowired
    private BotAppointmentService botAppointmentService;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        botAppointment.setTime(text);
        botUserService.setCommand(id, ActionEnum.SHOW_APPOINTMENTS);
        //постим аппоинтмент
        botAppointmentService.postAppointment(botAppointment);
        sendMessage.setText("Ваша запись:" + "\n"
                + "Доктор: " + botAppointment.getDoctor() + "\n"
                + "Процедура: " + botAppointment.getProcedure() + "\n"
                + "День: " + botAppointment.getDate() + "\n"
                + "Время: " + botAppointment.getTime() + "\n"
        );
        sendMessage.setReplyMarkup(keyboard());
    }

    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Мои записи");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }


    @Override
    public ActionEnum getKey() {
        return ActionEnum.MIDDLE_BOOKED;
    }
}
