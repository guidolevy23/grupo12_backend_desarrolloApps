package com.uade.tpo.gimnasio.dto.historial;

public record CalificacionCreateRequestDTO(
    Long asistenciaId,
    int estrellas,
    String comentario
) {}
