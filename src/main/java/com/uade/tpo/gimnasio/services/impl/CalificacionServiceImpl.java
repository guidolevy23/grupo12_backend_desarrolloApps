package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.models.entity.Calificacion;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.repositories.CalificacionRepository;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.CalificacionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Value("${app.calificaciones.period-hours:-1}")
    private long calificacionPeriodHours;

    private final CalificacionRepository calificacionRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final UserRepository userRepository;

    public CalificacionServiceImpl(CalificacionRepository calificacionRepository,
                                   AsistenciaRepository asistenciaRepository,
                                   UserRepository userRepository) {
        this.calificacionRepository = calificacionRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Calificacion createOrUpdate(Long usuarioId, Long asistenciaId, int estrellas, String comentario) {
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("Estrellas debe estar entre 1 y 5");
        }

        Asistencia asistencia = asistenciaRepository.findById(asistenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Asistencia no encontrada"));

        if (!asistencia.getUsuario().getId().equals(usuarioId)) {
            throw new SecurityException("No autorizado para calificar esta asistencia");
        }

        // Si la propiedad calificacionPeriodHours es -1 => desactivar límite
        if (calificacionPeriodHours >= 0) {
            if (asistencia.getTurno() == null || asistencia.getTurno().getFin() == null) {
                throw new IllegalStateException("No se puede verificar el periodo para calificar: información de turno faltante");
            }
            long hoursSinceEnd = Duration.between(asistencia.getTurno().getFin(), Instant.now()).toHours();
            if (hoursSinceEnd > calificacionPeriodHours) {
                throw new IllegalStateException("El periodo para calificar (" + calificacionPeriodHours + "h) expiró");
            }
        }

        Long turnoId = asistencia.getTurno().getId();

        Optional<Calificacion> existing = calificacionRepository.findByUsuario_IdAndTurno_Id(usuarioId, turnoId);
        Calificacion c;
        if (existing.isPresent()) {
            c = existing.get();
            c.setEstrellas(estrellas);
            c.setComentario(comentario);
        } else {
            c = new Calificacion();
            c.setUsuario(asistencia.getUsuario());
            c.setTurno(asistencia.getTurno());
            c.setEstrellas(estrellas);
            c.setComentario(comentario);
        }

        return calificacionRepository.save(c);
    }

    @Override
    public Optional<Calificacion> findByUsuarioIdAndTurnoId(Long usuarioId, Long turnoId) {
        return calificacionRepository.findByUsuario_IdAndTurno_Id(usuarioId, turnoId);
    }

    @Override
    public java.util.List<Calificacion> findByUsuarioId(Long usuarioId) {
        return calificacionRepository.findByUsuario_Id(usuarioId);
    }
}
