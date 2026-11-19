package org.example.antidoto.service;

import org.example.antidoto.dto.AnimalRequestDTO;
import org.example.antidoto.dto.AnimalResponseDTO;
import org.example.antidoto.entity.Animal;
import org.example.antidoto.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    private AnimalResponseDTO converter(Animal animal) {
        return new AnimalResponseDTO(
                animal.getId(),
                animal.getNomePopular(),
                animal.getNomeCientifico(),
                animal.getCategoria(),
                animal.getDescricao()
        );
    }

    public Page<AnimalResponseDTO> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable).map(this::converter);
    }

    public AnimalResponseDTO buscar(Long id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Animal não encontrado"));

        return converter(animal);
    }

    public AnimalResponseDTO cadastrar(AnimalRequestDTO dto) {
        Animal animal = new Animal();
        animal.setNomePopular(dto.nomePopular());
        animal.setNomeCientifico(dto.nomeCientifico());
        animal.setCategoria(dto.categoria());
        animal.setDescricao(dto.descricao());

        return converter(repository.save(animal));
    }

    public AnimalResponseDTO atualizar(Long id, AnimalRequestDTO dto) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Animal não encontrado"));

        animal.setNomePopular(dto.nomePopular());
        animal.setNomeCientifico(dto.nomeCientifico());
        animal.setCategoria(dto.categoria());
        animal.setDescricao(dto.descricao());

        return converter(repository.save(animal));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Animal não encontrado");
        }

        repository.deleteById(id);
    }
}
