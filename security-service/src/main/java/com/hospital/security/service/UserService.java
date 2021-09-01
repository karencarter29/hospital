package com.hospital.security.service;

import com.hospital.security.dto.UserDto;
import com.hospital.security.model.User;

import java.util.List;

public interface UserService {

    void register(UserDto userDto);

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    List<User> findAll();
}
