package org.example.antidoto.repository;

import org.example.antidoto.entity.Acidente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcidenteRepository
        extends JpaRepository<Acidente, Long> {

    List<Acidente> findByUsuarioId(Long usuarioId);

    List<Acidente> findByStatus(String status);
}
