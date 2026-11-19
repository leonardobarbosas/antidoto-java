package org.example.antidoto.repository;

import org.example.antidoto.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
