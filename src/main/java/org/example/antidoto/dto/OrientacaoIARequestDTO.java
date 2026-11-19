package org.example.antidoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrientacaoIARequestDTO(
        @NotBlank @Size(max = 100) String animal,
        @NotBlank @Size(max = 500) String sintomas
) {}