package com.uade.tpo.gimnasio.dto.historial;

import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseResponseDTO;

public record AsistenciaResponseDTO(
    Long id,
    ClaseResponseDTO clase,
    java.time.Instant fechaHoraCheckin
) {}
