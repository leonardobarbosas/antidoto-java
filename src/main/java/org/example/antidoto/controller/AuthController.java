package org.example.antidoto.controller;

import jakarta.validation.Valid;
import org.example.antidoto.dto.LoginRequestDTO;
import org.example.antidoto.dto.LoginResponseDTO;
import org.example.antidoto.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto
    ) {
        return authService.login(dto);
    }
}
