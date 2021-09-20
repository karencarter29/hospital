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
       try {
           if (text.equals("Main menu") ) {
               sendMessage.setChatId(id);
               sendMessage.setText("You going to main menu");
               botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
               sendMessage.setReplyMarkup(getKeyboard());
           }
           else {
               botAppointment.setTime(text);
               botUserService.setCommand(id, ActionEnum.SHOW_APPOINTMENTS);
               //постим аппоинтмент
               botAppointmentService.postAppointment(botAppointment);
               sendMessage.setText("\uD83E\uDE7A Your appointment:" + "\n"
                       + "\uD83E\uDE79 Doctor: " + botAppointment.getDoctor() + "\n"
                       + "\uD83D\uDC89 Procedure: " + botAppointment.getProcedure() + "\n"
                       + "\uD83D\uDCC6 Day: " + botAppointment.getDate() + "\n"
                       + "⌛ Time: " + botAppointment.getTime() + "\n"
               );
               sendMessage.setReplyMarkup(keyboard());
           }
       }
       catch (RuntimeException e) {
           sendMessage.setText("This appointment is already used ☹");
           sendMessage.setReplyMarkup(keyboardNot());
           botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
       }
    }

    @Autowired
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("My appointments");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    @Autowired
    public ReplyKeyboard keyboardNot() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Okey ☹");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Go!");
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
