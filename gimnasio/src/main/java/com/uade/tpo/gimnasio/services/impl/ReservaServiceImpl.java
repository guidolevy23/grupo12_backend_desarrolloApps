package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.services.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }
}

