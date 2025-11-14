package com.uade.tpo.gimnasio.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.gimnasio.models.entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva crearReserva(Long usuarioId, Long courseId);
    void cancelarReserva(Long id);
    List<Reserva> listarReservasPorUsuario(Long usuarioId);
}


