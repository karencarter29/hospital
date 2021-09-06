package com.gatewayapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header = httpServletRequest.getHeader(tokenConfig.getHeader());

        if (header == null || !header.startsWith(tokenConfig.getPrefix())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = header.replace(tokenConfig.getPrefix(), "");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            if (username != null) {

                List<String> roles = (List<String>) claims.get("roles");
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
    }
}
