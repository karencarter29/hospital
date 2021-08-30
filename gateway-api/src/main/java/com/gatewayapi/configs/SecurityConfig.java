//package com.gatewayapi.configs;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                //todo: add filtering
//                //.addFilterBefore()
//                .authorizeRequests()
//                .antMatchers("**/admin/**").hasRole("ADMIN")
//                .antMatchers("**/patient/**").hasRole("PATIENT")
//                .antMatchers("**/doctor/**").hasRole("DOCTOR")
//                .antMatchers("/register", "/auth").permitAll()
//                .anyRequest().authenticated();
//
//    }
//
//}
