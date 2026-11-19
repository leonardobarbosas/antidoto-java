package org.example.antidoto.dto;

public record AnimalResponseDTO(
        Long id,
        String nomePopular,
        String nomeCientifico,
        String categoria,
        String descricao
) {}
