package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.ProcedureService;
import com.example.alexthbot.fab.services.api.entities.Shift;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class ActionChooseDate extends Action {
    @Autowired
    private BotAppointment botAppointment;

    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private ServiceID serviceID;


    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
       try {
           botAppointment.setProcedure(text);
           botUserService.setCommand(id, ActionEnum.CHOOSE_TIME);
           Gson gson = new Gson();
           List<Shift> shifts = procedureService.getProceduresById(serviceID.getDoctorId());
           ObjectMapper mapper = new ObjectMapper();
           List<Shift> shiftList = mapper.convertValue(shifts, new TypeReference<List<Shift>>() {
                   }
           );
           for (int i = 0; i < shifts.size(); i++) {
               if (shiftList.get(i).getProcedureName().equals(text)) {
                   botAppointment.setId(String.valueOf(shiftList.get(i).getId()));
               }
           }
           sendMessage.setText("Choose data:\uD83D\uDCC5");
           sendMessage.setReplyMarkup(keyboard());
       }
       catch (RuntimeException e) {
           sendMessage.setText("There is no such dates \uD83D\uDC47");
           botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
       }
    }
    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        List<Shift> shifts = procedureService.getProceduresById(serviceID.getDoctorId());
        ObjectMapper mapper = new ObjectMapper();
        List<Shift> shiftList = mapper.convertValue(shifts,new TypeReference<List<Shift>>() {     }
        );
        shiftList.forEach(shift ->   keyboardRow.add(shift.getDate()));
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
