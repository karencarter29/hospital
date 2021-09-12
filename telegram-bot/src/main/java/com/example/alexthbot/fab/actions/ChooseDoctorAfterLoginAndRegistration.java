package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import com.example.alexthbot.fab.services.actions.AfterLoginFailedService;
import com.example.alexthbot.fab.services.actions.AfterLoginSuccessfullyService;
import com.example.alexthbot.fab.services.api.AuthServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChooseDoctorAfterLoginAndRegistration extends Action {
    @Autowired
    private AfterLoginSuccessfullyService successfullyService;
    @Autowired
    private CheckLogPass checkLogPass;
    @Autowired
    private AuthServiceApi authServiceApi;
    @Autowired
    private AfterLoginFailedService failedService;

    @Override
    public void action(Update update, SendMessage sendMessage, String text, String id) {
        if (authServiceApi.checkLoginAndPassword(checkLogPass).is2xxSuccessful()) {
            successfullyService.afterLogin(sendMessage, id);
        } else {
            failedService.afterLogin(sendMessage, id);
        }
    }

    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_DOCTOR_AFTER_LOGIN;
    }
}
