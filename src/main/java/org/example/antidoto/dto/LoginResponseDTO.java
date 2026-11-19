package org.example.antidoto.dto;

public record LoginResponseDTO(
        String token,
        String tipo,
        String nome,
        String email,
        String role
) {}
