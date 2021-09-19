package com.gatewayapi.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Getter
@ToString
@Component
public class TokenConfig {

    @Bean
    public TokenConfig getTokenConfig() {
        return new TokenConfig();
    }

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer_}")
    private String prefix;

    @Value("${security.jwt.secret:hospitalsecrettt}")
    private String secret;

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSecret() {
        return secret;
    }

}
