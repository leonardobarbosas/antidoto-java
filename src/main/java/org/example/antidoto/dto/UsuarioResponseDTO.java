package org.example.antidoto.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro
) {}
