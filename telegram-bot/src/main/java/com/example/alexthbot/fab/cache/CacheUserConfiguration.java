package com.example.alexthbot.fab.cache;

import com.example.alexthbot.fab.database.user.model.BotUser;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheUserConfiguration {

    @Bean("UserCache")
    public Cache<String, BotUser> cache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(365, TimeUnit.DAYS)
                .build();
    }

}
