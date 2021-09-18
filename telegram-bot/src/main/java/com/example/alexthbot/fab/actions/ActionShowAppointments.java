package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.BotAppointmentService;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import com.google.gson.Gson;
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
public class ActionShowAppointments extends Action {
    @Autowired
    private BotAppointmentService botAppointmentService;
    @Autowired
    private ServiceID serviceID;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        sendMessage.setChatId(id);
        try {
            Gson gson = new Gson();
            List<BotAppointment> botAppointments = new ArrayList<>();
            List<Appointment> appointments = botAppointmentService.getAppointments();
            Appointment[] appointments1 = gson.fromJson(String.valueOf(appointments), Appointment[].class);
            for (int i = 0; i < appointments1.length; i++) {
                BotAppointment botAppointment2 = new BotAppointment();
                botAppointment2.setProcedure(appointments1[i].getShift().getProcedureName());
                botAppointment2.setDate(appointments1[i].getShift().getDate());
                botAppointment2.setTime(appointments1[i].getShift().getStartTime());
                botAppointment2.setDoctor(serviceID.getDoctor());
                botAppointments.add(botAppointment2);
            }
            String s = "Ваша запись учтена, вы так же можете записаться к другому врачу.\n";
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
            sendMessage.setReplyMarkup(keyboard());
            for (int i = 0; i < botAppointments.size(); i++) {
                s +=
                        "Доктор: " + botAppointments.get(i).getDoctor() + "\n"
                                + "Процедура: " + botAppointments.get(i).getProcedure() + "\n"
                                + "День: " + botAppointments.get(i).getDate() + "\n"
                                + "Время: " + botAppointments.get(i).getTime() + "\n"
                ;
            }
            sendMessage.setText(s);
            botAppointments.clear();
        } catch (RuntimeException e) {
            sendMessage.setText("Ошибка получения записей");
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
