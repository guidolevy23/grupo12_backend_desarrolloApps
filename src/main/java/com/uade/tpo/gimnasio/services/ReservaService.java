package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservaService {
  Reserva crearReserva(Reserva reserva);
  void cancelarReserva(Long id);
  List<Reserva> listarReservasPorUsuario(Long usuarioId);
}

