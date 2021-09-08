package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.configuration.ConfigurationAppointment;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.BotAppointmentService;
import com.example.alexthbot.fab.services.ProcedureService;
import com.example.alexthbot.fab.services.ServiceID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActionBooked extends Action {
    @Autowired
    BotAppointment botAppointment;
    @Autowired
    BotAppointmentService botAppointmentService;
    @Autowired
    ConfigurationAppointment configurationAppointment;
    @Autowired
    ProcedureService procedureService;
    @Autowired
    ServiceID serviceID;


    @Override
    public void action(Update update, AbsSender absSender) {
        String id = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        botAppointment.setTime(text);

        botUserService.setCommand(id, ActionEnum.SHOW_APPOINTMENTS);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);

        //постим аппоинтмент
        //botAppointmentService.PostAppointment(botAppointment);


        sendMessage.setText("Ваша запись:"+ "\n"
                + "Доктор: " + botAppointment.getDoctor() + "\n"
                + "Процедура: " + botAppointment.getProcedure() + "\n"
                + "День: " + botAppointment.getDate() + "\n"
                + "Время: " + botAppointment.getTime() + "\n"
        );
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
