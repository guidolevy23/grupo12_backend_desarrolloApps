package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Asistencia;
import java.time.Instant;
import java.util.List;

public interface AsistenciaService {
  /** Historial de asistencias del usuario en un rango [from, to). */
  List<Asistencia> historial(Long userId, Instant fromInclusive, Instant toExclusive);

  /** (Para esta entrega) Registrar asistencia cuando corresponda. */
  Asistencia registrar(Long userId, Long claseId, Instant checkinTime);
}
