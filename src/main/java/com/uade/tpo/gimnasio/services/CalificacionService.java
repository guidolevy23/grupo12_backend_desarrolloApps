package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Calificacion;

import java.util.Optional;

public interface CalificacionService {
    Calificacion createOrUpdate(Long usuarioId, Long asistenciaId, int estrellas, String comentario);
    Optional<Calificacion> findByUsuarioIdAndTurnoId(Long usuarioId, Long turnoId);
    java.util.List<Calificacion> findByUsuarioId(Long usuarioId);
}
