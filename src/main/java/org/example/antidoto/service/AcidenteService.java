
package org.example.antidoto.service;

import org.example.antidoto.dto.AcidenteRequestDTO;
import org.example.antidoto.dto.AcidenteResponseDTO;
import org.example.antidoto.entity.Acidente;
import org.example.antidoto.entity.Animal;
import org.example.antidoto.entity.Usuario;
import org.example.antidoto.repository.AcidenteRepository;
import org.example.antidoto.repository.AnimalRepository;
import org.example.antidoto.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AcidenteService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AcidenteRepository acidenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;

    public AcidenteService(
            AcidenteRepository acidenteRepository,
            UsuarioRepository usuarioRepository,
            AnimalRepository animalRepository
    ) {
        this.acidenteRepository = acidenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
    }

    private AcidenteResponseDTO converter(Acidente acidente) {
        Animal animal = acidente.getAnimal();

        return new AcidenteResponseDTO(
                acidente.getId(),
                acidente.getUsuario().getId(),
                acidente.getUsuario().getNome(),
                animal != null ? animal.getId() : null,
                animal != null ? animal.getNomePopular() : null,
                acidente.getDataAcidente(),
                acidente.getDataRegistro(),
                acidente.getLatitude(),
                acidente.getLongitude(),
                acidente.getDescricao(),
                acidente.getStatus()
        );
    }

    @Transactional(readOnly = true)
    public List<AcidenteResponseDTO> listar() {
        return acidenteRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<AcidenteResponseDTO> listarPaginado(Pageable pageable) {
        return acidenteRepository.findAll(pageable)
                .map(this::converter);
    }

    @Transactional(readOnly = true)
    public AcidenteResponseDTO buscar(Long id) {
        Acidente acidente = acidenteRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Acidente não encontrado")
                );

        return converter(acidente);
    }

    @Transactional(readOnly = true)
    public List<AcidenteResponseDTO> listarPorUsuario(Long usuarioId) {
        return acidenteRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::converter)
                .toList();
    }

    @Transactional
    public AcidenteResponseDTO cadastrar(AcidenteRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() ->
                        new NoSuchElementException("Usuário não encontrado")
                );

        Animal animal = null;

        if (dto.animalId() != null) {
            animal = animalRepository.findById(dto.animalId())
                    .orElseThrow(() ->
                            new NoSuchElementException("Animal não encontrado")
                    );
        }

        Acidente acidente = new Acidente();
        acidente.setUsuario(usuario);
        acidente.setAnimal(animal);
        acidente.setDataAcidente(dto.dataAcidente());
        acidente.setLatitude(dto.latitude());
        acidente.setLongitude(dto.longitude());
        acidente.setDescricao(dto.descricao());
        acidente.setStatus("REGISTRADO");

        Acidente salvo = acidenteRepository.saveAndFlush(acidente);

        entityManager.refresh(salvo);

        return converter(salvo);
    }

    @Transactional
    public AcidenteResponseDTO atualizar(
            Long id,
            AcidenteRequestDTO dto
    ) {
        Acidente acidente = acidenteRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Acidente não encontrado")
                );

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() ->
                        new NoSuchElementException("Usuário não encontrado")
                );

        Animal animal = null;

        if (dto.animalId() != null) {
            animal = animalRepository.findById(dto.animalId())
                    .orElseThrow(() ->
                            new NoSuchElementException("Animal não encontrado")
                    );
        }

        acidente.setUsuario(usuario);
        acidente.setAnimal(animal);
        acidente.setDataAcidente(dto.dataAcidente());
        acidente.setLatitude(dto.latitude());
        acidente.setLongitude(dto.longitude());
        acidente.setDescricao(dto.descricao());

        return converter(acidenteRepository.saveAndFlush(acidente));
    }

    @Transactional
    public void excluir(Long id) {
        Acidente acidente = acidenteRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Acidente não encontrado")
                );

        acidenteRepository.delete(acidente);
    }
}
