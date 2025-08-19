package com.uade.tpo.gimnasio.dto.reservas;

import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseResponseDTO;

public record ReservaResponseDTO(
    Long id,
    ClaseResponseDTO clase,
    String estado,
    java.time.Instant creadaEn
) {}