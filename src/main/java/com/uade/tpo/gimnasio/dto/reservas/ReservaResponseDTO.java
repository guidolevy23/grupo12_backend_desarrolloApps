package com.uade.tpo.gimnasio.dto.reservas;

public record ReservaResponseDTO(
        Long id,
        String estado,
        String courseName,   // 🔄 antes: clase
        String branch,       // 🔄 antes: sede
        String horario        // sigue igual
) {}
