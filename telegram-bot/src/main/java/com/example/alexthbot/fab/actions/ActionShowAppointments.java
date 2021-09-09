package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.configuration.ConfigurationAppointment;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.entities.Appointment;
import com.example.alexthbot.fab.services.BotAppointmentService;
import com.example.alexthbot.fab.services.ServiceID;
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
import java.util.LinkedList;
import java.util.List;

@Component
public class ActionShowAppointments extends Action {
    @Autowired
    ConfigurationAppointment configurationAppointment;
    @Autowired
    BotAppointmentService botAppointmentService;
    @Autowired
    ServiceID serviceID;
    @Override
    public void action(Update update, AbsSender absSender) {
        Gson gson = new Gson();
        List<BotAppointment> botAppointments = new LinkedList<>() ;
        Appointment[] appointments1 = gson.fromJson(String.valueOf(botAppointmentService.GetAppointments()),Appointment[].class);
        for (int i = 0; i < appointments1.length; i++) {
            BotAppointment botAppointment2 = new BotAppointment();
            botAppointment2.setProcedure(appointments1[i].getShift().getProcedure().getProcedureName());
            botAppointment2.setDate(Arrays.toString(appointments1[i].getShift().getDate()));
            botAppointment2.setTime(Arrays.toString(appointments1[i].getShift().getStartTime()));
            botAppointment2.setDoctor(serviceID.getDoctor());
            botAppointments.add(botAppointment2);
        }
        String s = "";
        String id = update.getMessage().getChatId().toString();
        botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Ваша запись учтена, вы так же можете записаться к другому врачу.");
        sendMessage.setReplyMarkup(keyboard());
        for (int i = 0; i < botAppointments.size(); i++) {
            s+=
                             "Доктор: " + botAppointments.get(i).getDoctor() + "\n"
                            + "Процедура: " + botAppointments.get(i).getProcedure() + "\n"
                            + "День: " + botAppointments.get(i).getDate() + "\n"
                            + "Время: " + botAppointments.get(i).getTime() + "\n"

                    ;
        }
        sendMessage.setText(s);
        botAppointments.clear();
        try {
            absSender.execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Записаться к другому врачу");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    @Override
    public ActionEnum getKey() {
        return ActionEnum.SHOW_APPOINTMENTS;
    }
}
