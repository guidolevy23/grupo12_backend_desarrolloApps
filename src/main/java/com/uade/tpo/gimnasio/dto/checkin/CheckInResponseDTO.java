package com.uade.tpo.gimnasio.dto.checkin;

import java.time.Instant;

public record CheckInResponseDTO(
    boolean success,
    String message,
    Long historialId,
    Instant timestamp
) {}
