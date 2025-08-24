package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
