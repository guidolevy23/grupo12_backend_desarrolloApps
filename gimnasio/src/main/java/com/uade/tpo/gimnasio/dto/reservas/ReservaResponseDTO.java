package com.uade.tpo.gimnasio.dto.reservas;

import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseResponseDTO;

public record ReservaResponseDTO(Long id, String estado, String clase, String sede, String horario) {}