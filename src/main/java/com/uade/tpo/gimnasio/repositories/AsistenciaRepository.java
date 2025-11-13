package com.uade.tpo.gimnasio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.gimnasio.models.entity.Asistencia;

import java.time.Instant;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByUsuario_IdOrderByCheckInAtDesc(Long usuarioId);
    List<Asistencia> findByUsuario_IdAndCheckInAtBetweenOrderByCheckInAtDesc(
            Long usuarioId, Instant desde, Instant hasta);

    List<Asistencia> findByUsuarioId(Long usuarioId);
}
