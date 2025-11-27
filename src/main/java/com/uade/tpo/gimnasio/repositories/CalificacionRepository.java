package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    Optional<Calificacion> findByUsuario_IdAndTurno_Id(Long usuarioId, Long turnoId);
    boolean existsByUsuario_IdAndTurno_Id(Long usuarioId, Long turnoId);
    java.util.List<Calificacion> findByUsuario_Id(Long usuarioId);
}
