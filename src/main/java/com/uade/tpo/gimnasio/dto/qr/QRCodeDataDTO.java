package com.uade.tpo.gimnasio.dto.qr;

import java.time.Instant;

public record QRCodeDataDTO(
    Long classId,
    String sessionId,
    Instant timestamp,
    String type,
    String signature
) {}
