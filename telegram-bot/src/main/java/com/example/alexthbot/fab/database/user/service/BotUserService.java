package com.example.alexthbot.fab.database.user.service;

import com.example.alexthbot.fab.actions.router.ActionEnum;
import com.example.alexthbot.fab.actions.router.Role;
import com.example.alexthbot.fab.database.user.model.BotUser;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;


@Service
public class BotUserService {

    @Autowired
    protected Cache<String, BotUser> cache;
    @Autowired
    BotUser botUser;

    public void setCommand(String chatId, ActionEnum actionEnum){
        changeUser(chatId,botUser -> botUser.setCommand(actionEnum.getCommand()));
    }

    public void setFirstName(String chatId, String name){
        changeUser(chatId,botUser -> botUser.setFirstName(name));
    }

    public void setSecondName(String chatId, String secondName){
        changeUser(chatId,botUser -> botUser.setSecondName(secondName));
    }

    public void setRole(Long chatId, Role role){
        changeUser(chatId.toString(),botUser -> botUser.setRole(role));
    }
    public Long getId(){
        return botUser.getId();
    }


    public void setLogin(String chatId, String login){
        changeUser(chatId,botUser -> botUser.setLogin(login));
    }

    public void setPassword(String chatId, String password){
        changeUser(chatId,botUser -> botUser.setPassword(password));
    }

    public BotUser user(String chatId){
        return cache.getIfPresent(chatId);
    }

    public void saveUser (String chatId , BotUser botUser){
        cache.put(chatId,botUser);
    }

    private void changeUser(String chatId, Consumer<BotUser> action){
        BotUser botUser = user(chatId);
        action.accept(botUser);
        saveUser(chatId,botUser);
    }

    public String getFirstName(String chatId){
        return Objects.requireNonNull(cache.getIfPresent(chatId)).getFirstName();
    }

    public String getSecondName(String chatId){
        return Objects.requireNonNull(cache.getIfPresent(chatId)).getSecondName();
    }
    public String getLogin(String chatId){
        return (cache.getIfPresent(chatId)).getLogin();
    }

}
