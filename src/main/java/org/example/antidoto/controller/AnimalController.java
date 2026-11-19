
package org.example.antidoto.controller;

import jakarta.validation.Valid;
import org.example.antidoto.dto.AnimalRequestDTO;
import org.example.antidoto.dto.AnimalResponseDTO;
import org.example.antidoto.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public Page<AnimalResponseDTO> listarPaginado(Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public AnimalResponseDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> cadastrar(
            @Valid @RequestBody AnimalRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public AnimalResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AnimalRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
