package com.example.alexthbot.fab.actions;

import com.example.alexthbot.fab.actions.parent.Action;
import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.database.user.model.CheckLogPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ActionWaitLoginOrRegistration extends Action {
    @Autowired
    CheckLogPass checkLogPass;

    @Override
    public void action(Update update, AbsSender absSender) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (text.equals("Логин")) {
            String id = update.getMessage().getChatId().toString();
            botUserService.setCommand(id, ActionEnum.LOGIN_AUTH);
            sendMessage.setText("Введите логин:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else if (text.equals("Регистрация")) {
            botUserService.setCommand(chatId, ActionEnum.CHOOSE_FIRST_NAME);
            String firstName = update.getMessage().getFrom().getFirstName();
            botUserService.setLogin(chatId, firstName + "_Hospital");
            sendMessage.setText("Ваш логин будет автоматически сгенерирован, введите имя:");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        } else {
            sendMessage.setText("Нажмите на кнопку Логин или Регистрация");
        }
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ActionEnum getKey() {
        return ActionEnum.CHOOSE_LOGIN_OR_REGISTRATION;
    }
}
