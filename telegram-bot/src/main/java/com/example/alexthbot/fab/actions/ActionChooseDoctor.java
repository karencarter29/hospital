package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.ProcedureService;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.example.alexthbot.fab.services.api.entities.Shift;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
      // try {
           serviceID.setDoctor(text);
           Gson gson = new Gson();
           Doctor[] doctors = gson.fromJson(String.valueOf(doctorService.getDoctors()), Doctor[].class);
           for (int i = 0; i < doctors.length; i++) {
               if (doctors[i].getSpeciality().getSpecialityName().equals(text)) {
                   serviceID.setDoctorId(doctors[i].getId());
               }
           }
           botAppointment.setDoctor(text);
           botUserService.setCommand(id, ActionEnum.CHOOSE_DATE);
           sendMessage.setReplyMarkup(keyboardTooth());
           sendMessage.setText("Choose a procedure:\uD83C\uDFE5 \n(For the first time we advise you to choose a consultation)");
      // }
//       catch (RuntimeException e) {
//           sendMessage.setText("There is no such procedure \uD83D\uDC47");
//           botUserService.setCommand(id, ActionEnum.CHOOSE_DOCTOR_SECOND_TIME);
//       }

    }


    public ReplyKeyboard keyboardTooth() {
        Gson gson = new Gson();
        KeyboardRow keyboardRow = new KeyboardRow();
        List<Shift> shifts = procedureService.getProceduresById(serviceID.getDoctorId());
        ObjectMapper mapper = new ObjectMapper();
        List<Shift> shiftList = mapper.convertValue(shifts,new TypeReference<List<Shift>>() {     }
        );
        shiftList.forEach(shift ->   keyboardRow.add(shift.getProcedureName()));
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
