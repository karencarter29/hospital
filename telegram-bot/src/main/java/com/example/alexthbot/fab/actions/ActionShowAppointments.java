package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.BotAppointmentService;
import com.example.alexthbot.fab.services.api.ProcedureService;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import com.example.alexthbot.fab.services.api.entities.Shift;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    ProcedureService procedureService;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        sendMessage.setChatId(id);
        try {
            Gson gson = new Gson();
            List<Appointment> botAppointments = new ArrayList<>();
        List<Appointment> appointments = botAppointmentService.getAppointments();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Appointment> appList = objectMapper.convertValue(appointments,new TypeReference<List<Appointment>>() {     }
        );
            String s = "Your record is taken into account, you can also make an appointment with another doctor.\n";
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
            sendMessage.setReplyMarkup(keyboard());
            for (int i = 0; i < appList.size(); i++) {
                s +=
                                 "\n" +"Doctor: " + appList.get(i).getShift().getSpecialityName() + "\n"
                                + "Procedure: " + appList.get(i).getShift().getProcedureName() + "\n"
                                + "Day: " + appList.get(i).getShift().getDate() + "\n"
                                + "Time: " + appList.get(i).getShift().getStartTime() + "\n" + "\n"
                ;
            }
            sendMessage.setText(s);
            botAppointments.clear();
        }  catch (RuntimeException e) {
            sendMessage.setText("Error getting appointments \uD83D\uDC47");
            botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
        }
    }
@Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Make an appointment with another doctor");
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
