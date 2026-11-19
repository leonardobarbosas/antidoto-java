package org.example.antidoto.controller;

import jakarta.validation.Valid;
import org.example.antidoto.dto.AcidenteRequestDTO;
import org.example.antidoto.dto.AcidenteResponseDTO;
import org.example.antidoto.service.AcidenteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acidentes")
public class AcidenteController {

    private final AcidenteService service;

    public AcidenteController(AcidenteService service) {
        this.service = service;
    }

    @GetMapping
    public List<AcidenteResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/paginado")
    public Page<AcidenteResponseDTO> listarPaginado(Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AcidenteResponseDTO> listarPorUsuario(
            @PathVariable Long usuarioId
    ) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public AcidenteResponseDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<AcidenteResponseDTO> cadastrar(
            @Valid @RequestBody AcidenteRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public AcidenteResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AcidenteRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
