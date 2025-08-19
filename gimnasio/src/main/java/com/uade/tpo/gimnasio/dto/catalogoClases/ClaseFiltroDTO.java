package com.uade.tpo.gimnasio.dto.catalogoClases;

public record ClaseFiltroDTO(
    Long sedeId,
    Long disciplinaId,
    java.time.LocalDate fecha
) {}