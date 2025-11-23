package com.uade.tpo.gimnasio.dto.historial;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CalificarAsistenciaRequestDTO(
    @NotNull(message = "El rating es obligatorio")
    @Min(value = 1, message = "El rating debe ser mínimo 1")
    @Max(value = 5, message = "El rating debe ser máximo 5")
    Integer rating,
    
    String comment  // Comentario opcional
) {}
