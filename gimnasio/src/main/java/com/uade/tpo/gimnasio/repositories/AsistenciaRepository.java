package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByUsuario_IdOrderByCheckInAtDesc(Long usuarioId);
    List<Asistencia> findByUsuario_IdAndCheckInAtBetweenOrderByCheckInAtDesc(
            Long usuarioId, Instant desde, Instant hasta);
}
