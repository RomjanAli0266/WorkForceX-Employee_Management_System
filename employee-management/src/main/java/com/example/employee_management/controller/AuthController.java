package com.example.employee_management.controller;

import com.example.employee_management.dto.LoginRequestDTO;
import com.example.employee_management.dto.LoginResponseDTO;
import com.example.employee_management.dto.UserDTO;
import com.example.employee_management.dto.UserRequestDTO;
import com.example.employee_management.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(security = {})
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(
            @Valid @RequestBody UserRequestDTO request) {

        return authService.register(request);
    }

    @Operation(security = {})
    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request) {

        return authService.login(request);
    }
}
