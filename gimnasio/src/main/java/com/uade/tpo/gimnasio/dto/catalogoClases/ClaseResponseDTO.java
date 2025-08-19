package com.uade.tpo.gimnasio.dto.catalogoClases;

public record ClaseResponseDTO(
    Long id,
    String sede,
    String disciplina,
    String profesor,
    java.time.Instant fechaHoraInicio,
    Integer duracionMin,
    Integer cupoTotal,
    Integer cuposDisponibles
) {}