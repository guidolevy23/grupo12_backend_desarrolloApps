package com.uade.tpo.gimnasio.dto.historial;

public record CalificacionResponseDTO(
    Long id,
    Long turnoId,
    int estrellas,
    String comentario
) {}
