package com.hospital.security.service.Impl;

import com.hospital.security.config.SecretConfig;
import com.hospital.security.dto.AuthenticationRequestDto;
import com.hospital.security.dto.UserDto;
import com.hospital.security.model.Role;
import com.hospital.security.model.User;
import com.hospital.security.repository.RoleRepository;
import com.hospital.security.repository.UserRepository;
import com.hospital.security.security.jwt.JwtTokenProvider;
import com.hospital.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecretConfig secretConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder,
                           AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                           SecretConfig secretConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.secretConfig = secretConfig;
    }

    public void addRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public String login(AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return secretConfig.getPrefix() + jwtTokenProvider.createToken(getUserDtoFromUser(user), user.getRoles());
    }

    @Override
    public String getTokenForUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        UserDto dto = getUserDtoFromUser(user);
        return secretConfig.getPrefix() + jwtTokenProvider.createToken(dto, user.getRoles());
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private UserDto getUserDtoFromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(),user.getFirstName(), user.getSecondName());
    }
}
