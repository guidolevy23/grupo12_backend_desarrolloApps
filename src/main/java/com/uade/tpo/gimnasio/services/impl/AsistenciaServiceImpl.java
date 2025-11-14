package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final ReservaRepository reservaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository, 
                                 ReservaRepository reservaRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> historialPorUsuario(Long usuarioId) {
        return asistenciaRepository.findByUsuario_IdOrderByCheckInAtDesc(usuarioId);
    }

    @Override
    public List<Asistencia> historialPorUsuarioConFiltros(Long usuarioId, Instant fechaInicio, Instant fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return asistenciaRepository.findByUsuario_IdAndCheckInAtBetweenOrderByCheckInAtDesc(usuarioId, fechaInicio, fechaFin);
        } else {
            return historialPorUsuario(usuarioId);
        }
    }

    @Override
    @Transactional
    public void convertirReservasPasadasAAsistencias(Long usuarioId) {
        // Get all confirmed reservations for this user
        List<Reserva> reservasConfirmadas = reservaRepository
                .findByUsuario_IdAndEstadoOrderByIdDesc(usuarioId, Reserva.Estado.CONFIRMADA);

        LocalDateTime ahora = LocalDateTime.now();

        for (Reserva reserva : reservasConfirmadas) {
            // Check if the class has ended (is in the past)
            if (reserva.getCourse().getEndsAt() != null && 
                reserva.getCourse().getEndsAt().isBefore(ahora)) {
                
                // Check if attendance record already exists for this reservation
                boolean yaExisteAsistencia = asistenciaRepository.findByUsuarioId(usuarioId)
                        .stream()
                        .anyMatch(a -> a.getCourse().getId().equals(reserva.getCourse().getId()) &&
                                      a.getCheckInAt().isAfter(reserva.getCourse().getStartsAt()
                                              .atZone(ZoneId.systemDefault()).toInstant()) &&
                                      a.getCheckInAt().isBefore(reserva.getCourse().getEndsAt()
                                              .atZone(ZoneId.systemDefault()).toInstant()));

                if (!yaExisteAsistencia) {
                    // Create attendance record
                    Asistencia asistencia = new Asistencia();
                    asistencia.setUsuario(reserva.getUsuario());
                    asistencia.setCourse(reserva.getCourse());
                    asistencia.setTurno(null);
                    // Set check-in time to the class end time
                    asistencia.setCheckInAt(reserva.getCourse().getEndsAt()
                            .atZone(ZoneId.systemDefault()).toInstant());
                    asistenciaRepository.save(asistencia);

                    // Update reservation status
                    reserva.setEstado(Reserva.Estado.ASISTIDA);
                    reservaRepository.save(reserva);
                }
            }
        }
    }
}

