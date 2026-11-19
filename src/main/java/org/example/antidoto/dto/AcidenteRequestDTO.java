package org.example.antidoto.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AcidenteRequestDTO(

        @NotNull(message = "O usuário é obrigatório")
        Long usuarioId,

        Long animalId,

        @NotNull(message = "A data do acidente é obrigatória")
        @PastOrPresent(message = "A data do acidente não pode ser futura")
        LocalDateTime dataAcidente,

        @DecimalMin(value = "-90.0", message = "Latitude inválida")
        @DecimalMax(value = "90.0", message = "Latitude inválida")
        BigDecimal latitude,

        @DecimalMin(value = "-180.0", message = "Longitude inválida")
        @DecimalMax(value = "180.0", message = "Longitude inválida")
        BigDecimal longitude,

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
        String descricao

) {}
