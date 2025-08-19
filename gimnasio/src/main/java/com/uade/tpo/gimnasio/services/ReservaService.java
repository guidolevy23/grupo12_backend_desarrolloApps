package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservaService {
  /**
   * Crea la reserva validando:
   * - que la clase no haya comenzado,
   * - que haya cupo disponible,
   * - que el usuario no tenga ya una reserva confirmada en esa clase.
   */
  Reserva crear(Long userId, Long claseId);

  /** Cancela una reserva del propio usuario (según política). */
  void cancelar(Long userId, Long reservaId);

  /** Lista las próximas reservas del usuario (puede filtrar por estado en el controller). */
  Page<Reserva> listarMias(Long userId, Reserva.Estado estado, Pageable pageable);
}
