package com.gatewayapi.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;


@Getter
@ToString
public class TokenConfig {

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer_}")
    private String prefix;

    @Value("${security.jwt.secret:hospitalsecrettt}") //hospitalsecrettt
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
