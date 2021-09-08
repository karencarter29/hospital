package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.configuration.ConfigurationAppointment;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.ProcedureService;
import com.example.alexthbot.fab.services.ServiceID;
import com.example.alexthbot.fab.services.Shift;
import com.example.alexthbot.fab.services.TimeForBook;
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
import java.util.List;

@Component
public class ActionChooseTime extends Action {
    @Autowired
    BotAppointment botAppointment;
    @Autowired
    TimeForBook timeForBook;
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
        botAppointment.setDate(text);
        botUserService.setCommand(id, ActionEnum.MIDDLE_BOOKED);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Выберите время:");
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
        Gson gson = new Gson();
        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            String s = Arrays.toString(shifts[i].getStartTime());
            String time = s.substring(13, s.length()-15);
            time=time.replace(',',':');
            keyboardRow.add(time);
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
        return ActionEnum.CHOOSE_TIME;
    }
}
