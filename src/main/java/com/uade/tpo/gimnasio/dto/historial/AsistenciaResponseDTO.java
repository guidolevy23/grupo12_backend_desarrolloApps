package com.uade.tpo.gimnasio.dto.historial;

public record AsistenciaResponseDTO(
    Long id, 
    String nombreClase, 
    String nombreSede, 
    String fecha, 
    int duracionMinutos,
    String profesor
) {}

