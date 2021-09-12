package com.hospital.security.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@ToString
@Component
public class SecretConfig {

    @Value("${config.jwt.header:Authorization}")
    private String header;

    @Value("${config.jwt.prefix:Bearer_}")
    private String prefix;

    @Value("${config.jwt.secret:hospitalsecrettt}")
    private String secret;
}
