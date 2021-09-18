package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.ProcedureService;
import com.example.alexthbot.fab.services.api.entities.Shift;
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
        botAppointment.setProcedure(text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_TIME);
        Gson gson = new Gson();
        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            if (shifts[i].getProcedureName().equals(text)) {
                botAppointment.setId(shifts[i].getId());
            }
        }
        sendMessage.setText("Choose data: ");
        sendMessage.setReplyMarkup(keyboard());
    }
    @Override
    public ReplyKeyboard keyboard() {
        KeyboardRow keyboardRow = new KeyboardRow();
        Gson gson = new Gson();
        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
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
