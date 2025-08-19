package com.uade.tpo.gimnasio.dto.auth;

public record VerifyOtpDTO(
    @jakarta.validation.constraints.Email String email,
    String otp
) {}