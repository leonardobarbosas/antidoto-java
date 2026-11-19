package org.example.antidoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AnimalRequestDTO(

        @NotBlank(message = "O nome popular é obrigatório")
        @Size(max = 100)
        String nomePopular,

        @Size(max = 150)
        String nomeCientifico,

        @NotBlank(message = "A categoria é obrigatória")
        @Pattern(
                regexp = "SERPENTE|ESCORPIAO|ARANHA|OUTRO",
                message = "A categoria deve ser SERPENTE, ESCORPIAO, ARANHA ou OUTRO"
        )
        String categoria,

        @Size(max = 500)
        String descricao
) {}
