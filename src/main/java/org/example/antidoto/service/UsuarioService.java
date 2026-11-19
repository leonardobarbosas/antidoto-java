
package org.example.antidoto.service;

import org.example.antidoto.dto.UsuarioRequestDTO;
import org.example.antidoto.dto.UsuarioResponseDTO;
import org.example.antidoto.entity.Usuario;
import org.example.antidoto.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    private UsuarioResponseDTO converter(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDataCadastro()
        );
    }

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public Page<UsuarioResponseDTO> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::converter);
    }

    public UsuarioResponseDTO buscar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Usuário não encontrado")
                );

        return converter(usuario);
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado"
            );
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());

        usuario.setSenhaHash(
                passwordEncoder.encode(dto.senha())
        );

        Usuario salvo = repository.saveAndFlush(usuario);

        return converter(
                repository.findById(salvo.getId())
                        .orElseThrow()
        );
    }

    public UsuarioResponseDTO atualizar(
            Long id,
            UsuarioRequestDTO dto
    ) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Usuário não encontrado")
                );

        repository.findByEmail(dto.email())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "E-mail já cadastrado"
                    );
                });

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());

        usuario.setSenhaHash(
                passwordEncoder.encode(dto.senha())
        );

        Usuario salvo = repository.saveAndFlush(usuario);

        return converter(salvo);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado");
        }

        repository.deleteById(id);
    }
}
