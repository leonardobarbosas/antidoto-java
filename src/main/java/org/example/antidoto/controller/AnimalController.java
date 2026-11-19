
package org.example.antidoto.controller;

import org.example.antidoto.entity.Animal;
import org.example.antidoto.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<Animal> listar() {
        return animalRepository.findAll();
    }
}
