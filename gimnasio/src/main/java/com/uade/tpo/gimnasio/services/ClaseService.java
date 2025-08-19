package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Clase;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClaseService {
  /**
   * Lista clases con filtros combinables (sede, disciplina, fecha).
   * La fecha representa el día (00:00–23:59) en la zona del sistema.
   */
  Page<Clase> listar(Long sedeId, Long disciplinaId, LocalDate fecha, Pageable pageable);

  /** Detalle de una clase. */
  Clase detalle(Long claseId);

  /** Cantidad de cupos confirmados (para calcular cupos disponibles en el controller). */
  int cuposTomados(Long claseId);
}
