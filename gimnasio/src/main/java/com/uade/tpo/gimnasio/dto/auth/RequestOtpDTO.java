package com.uade.tpo.gimnasio.dto.auth;


public record RequestOtpDTO(
    @jakarta.validation.constraints.Email String email
) {}
