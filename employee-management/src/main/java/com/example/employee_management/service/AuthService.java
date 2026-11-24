package com.example.employee_management.service;

import com.example.employee_management.JwtService.JwtService;
import com.example.employee_management.dto.LoginRequestDTO;
import com.example.employee_management.dto.LoginResponseDTO;
import com.example.employee_management.dto.UserDTO;
import com.example.employee_management.dto.UserRequestDTO;
import com.example.employee_management.entity.UserEntity;
import com.example.employee_management.exception.DuplicateUsernameException;
import com.example.employee_management.mapper.UserMapper;
import com.example.employee_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDTO register(UserRequestDTO request) {

        // Check whether username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException(
                    "Username already exists: " + request.getUsername()
            );
        }

        // Convert DTO to Entity
        UserEntity user = userMapper.toEntity(request);

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Default role
        user.setRole("USER");

        // Save user
        UserEntity savedUser = userRepository.save(user);

        // convert Entity to DTO
        return userMapper.toDTO(savedUser);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password")
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(
                user.getUsername()
        );

        return new LoginResponseDTO(token);
    }
}
