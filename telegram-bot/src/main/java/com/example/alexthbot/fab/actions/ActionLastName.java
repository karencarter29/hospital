package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.service.BotUserService;
import com.example.alexthbot.fab.services.api.DoctorService;
import com.example.alexthbot.fab.services.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionLastName extends Action {
    @Autowired
    private BotUserService botUserService;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        botUserService.setSecondName(id, text);
        botUserService.setCommand(id, ActionEnum.REGISTRATION_WAITING_PASSWORD);
        sendMessage.setText("Введите пароль:");
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_LAST_NAME;
    }


}
