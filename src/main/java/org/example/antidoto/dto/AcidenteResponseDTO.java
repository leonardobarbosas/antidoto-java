package org.example.antidoto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AcidenteResponseDTO(
        Long id,
        Long usuarioId,
        String nomeUsuario,
        Long animalId,
        String nomeAnimal,
        LocalDateTime dataAcidente,
        LocalDateTime dataRegistro,
        BigDecimal latitude,
        BigDecimal longitude,
        String descricao,
        String status
) {}
