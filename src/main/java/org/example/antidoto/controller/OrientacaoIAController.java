
package org.example.antidoto.controller;

import jakarta.validation.Valid;
import org.example.antidoto.dto.OrientacaoIARequestDTO;
import org.example.antidoto.service.OrientacaoIAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ia")
public class OrientacaoIAController {

    private final OrientacaoIAService service;

    public OrientacaoIAController(OrientacaoIAService service) {
        this.service = service;
    }

    @PostMapping("/orientacoes")
    public ResponseEntity<Map<String, String>> orientar(
            @Valid @RequestBody OrientacaoIARequestDTO request) {

        String orientacao = service.gerarOrientacao(
                request.animal(),
                request.sintomas()
        );

        return ResponseEntity.ok(Map.of(
                "orientacao", orientacao
        ));
    }
}
