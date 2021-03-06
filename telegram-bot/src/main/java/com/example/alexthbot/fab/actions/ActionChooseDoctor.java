package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.ProcedureService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
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
public class ActionChooseDoctor extends Action {
    @Autowired
    private BotAppointment botAppointment;
    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ServiceID serviceID;


    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        serviceID.setDoctor(text);
        Gson gson = new Gson();
        Doctor[] doctors = gson.fromJson(String.valueOf(doctorService.get()), Doctor[].class);
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getSpeciality().getSpecialityName().equals(text)) {
                serviceID.setDoctorId(doctors[i].getId());
            }
        }
        botAppointment.setDoctor(text);
        botUserService.setCommand(id, ActionEnum.CHOOSE_DATE);
        sendMessage.setReplyMarkup(keyboardTooth());
        sendMessage.setText("???????????????? ??????????????????: \n(?? ???????????? ?????? ???????????????? ?????????????? ????????????????????????)");


    }


    public ReplyKeyboard keyboardTooth() {
        Gson gson = new Gson();
        KeyboardRow keyboardRow = new KeyboardRow();
        // List<Shift> shifts =  procedureService.getProceduresById(serviceID.getDoctorId());
//        for (int i = 0; i < shifts.size(); i++) {
//            keyboardRow.add(shifts.get(i).getProcedure().getProcedureName());
//        }
        //  ClassCastException   shifts.stream().forEach(prod1 -> keyboardRow.add(prod1.getProcedure().getProcedure()));

        Shift[] shifts = gson.fromJson(String.valueOf(procedureService.getProceduresById(serviceID.getDoctorId())), Shift[].class);
        for (int i = 0; i < shifts.length; i++) {
            keyboardRow.add(shifts[i].getProcedureName());
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
        return ActionEnum.CHOOSE_DOCTOR;
    }
}
