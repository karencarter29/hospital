package com.hospital.security.service.Impl;

import com.hospital.security.dto.UserDto;
import com.hospital.security.model.Role;
import com.hospital.security.model.User;
import com.hospital.security.repository.RoleRepository;
import com.hospital.security.repository.UserRepository;
import com.hospital.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void register(UserDto userDto) {
        Role userRole = roleRepository.findByName("ROLE_PATIENT");
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new BadCredentialsException("Username already exist");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setSecondName(userDto.getSecondName());
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
