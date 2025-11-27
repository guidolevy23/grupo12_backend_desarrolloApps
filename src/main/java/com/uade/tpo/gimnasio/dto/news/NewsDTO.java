package com.uade.tpo.gimnasio.dto.news;


public record NewsDTO(
        String id,
        String titulo,
        String descripcion,
        String imagenUrl,
        String fecha,
        String tipo
) {}