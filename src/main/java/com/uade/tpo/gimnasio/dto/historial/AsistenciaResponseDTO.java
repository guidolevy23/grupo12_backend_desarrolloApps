package com.uade.tpo.gimnasio.dto.historial;

import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseResponseDTO;

public record AsistenciaResponseDTO(Long id, String clase, String sede, String fecha, int duracionMinutos) {}

