package com.uade.tpo.gimnasio.dto.perfil;

public record PerfilResponseDTO(
    Long id,
    String nombre,
    String email,
    String fotoUrl
) {}
