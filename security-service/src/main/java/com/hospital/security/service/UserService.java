package com.hospital.security.service;

import com.hospital.security.dto.AuthenticationRequestDto;
import com.hospital.security.dto.UserDto;
import com.hospital.security.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void register(UserDto userDto);

    User findByUsername(String username);

    User findById(UUID id);

    void delete(UUID id);

    List<User> findAll();

    String login(AuthenticationRequestDto requestDto);

    String getTokenForUser(UserDto userDto);
}
