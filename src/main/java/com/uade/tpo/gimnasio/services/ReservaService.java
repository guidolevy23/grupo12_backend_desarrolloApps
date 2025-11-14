package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva crearReserva(Long usuarioId, Long courseId);
    void cancelarReserva(Long id);
    List<Reserva> listarReservasPorUsuario(Long usuarioId);
    void marcarAsistencia(Long reservaId);
}


