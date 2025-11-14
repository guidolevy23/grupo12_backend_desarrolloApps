package com.uade.tpo.gimnasio.dto.historial;

public record AsistenciaResponseDTO(
    Long id,
    String courseName,       // 🔄 antes: nombreClase
    String branch,           // 🔄 antes: nombreSede
    String fecha,            // fecha formateada del check-in
    Integer durationMinutes, // 🔄 antes: duracionMinutos (ahora Integer por si es null)
    String professor          // nombre del profesor
) {}
