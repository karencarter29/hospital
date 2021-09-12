package com.gatewayapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenParser {
    private final TokenConfig tokenConfig;

    @Autowired
    public TokenParser(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    public String getUserId(String header) {
        String token = header.replace(tokenConfig.getPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(tokenConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", String.class);
    }
}
