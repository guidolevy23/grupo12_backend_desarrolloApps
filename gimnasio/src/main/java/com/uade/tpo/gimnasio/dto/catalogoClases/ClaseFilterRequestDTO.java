package com.uade.tpo.gimnasio.dto.catalogoClases;

import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.Sede;

public record ClaseFilterRequestDTO(Sede sede, Disciplina disciplina) {}