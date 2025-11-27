package com.uade.tpo.gimnasio.dto.historial;

public record CalificacionResponseDTO(
    Long id,
    Long course_id,
    int estrellas,
    String comentario
) {}
