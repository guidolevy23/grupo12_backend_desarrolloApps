package com.uade.tpo.gimnasio.dto.checkin;

import jakarta.validation.constraints.NotNull;

public record CheckInRequestDTO(
    @NotNull(message = "Reservation ID is required")
    Long reservaId,
    
    @NotNull(message = "QR code is required")
    String qrCode
) {}
