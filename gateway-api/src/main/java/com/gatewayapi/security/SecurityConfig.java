package com.gatewayapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenConfig tokenConfig;

    @Bean
    public TokenConfig getTokenConfig() {
        return new TokenConfig();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new SecurityFilter(tokenConfig), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("**/admin/**").hasRole("ADMIN")
                .antMatchers("**/patient/**").hasRole("PATIENT")
                .antMatchers("**/doctor/**").hasRole("DOCTOR")
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated();
    }
}
