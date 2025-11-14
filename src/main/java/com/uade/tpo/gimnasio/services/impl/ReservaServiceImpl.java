package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.repositories.CourseRepository;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.ReservaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final AsistenciaRepository asistenciaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              CourseRepository courseRepository,
                              UserRepository userRepository,
                              AsistenciaRepository asistenciaRepository) {
        this.reservaRepository = reservaRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    @Transactional
    public Reserva crearReserva(Long usuarioId, Long courseId) {

        // Validar y traer usuario real
        var usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID " + usuarioId));

        // Validar y traer curso real
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID " + courseId));

        // Crear reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setCourse(course);

        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public void cancelarReserva(Long id) {
        var reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID " + id));

        reserva.setEstado(Reserva.Estado.CANCELADA);
        reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional
    public void marcarAsistencia(Long reservaId) {
        var reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID " + reservaId));

        if (reserva.getEstado() != Reserva.Estado.CONFIRMADA) {
            throw new IllegalStateException("Solo se pueden marcar asistencias de reservas confirmadas");
        }

        // Crear registro de asistencia
        Asistencia asistencia = new Asistencia();
        asistencia.setUsuario(reserva.getUsuario());
        asistencia.setCourse(reserva.getCourse());
        asistencia.setCheckInAt(Instant.now());
        asistencia.setTurno(null); // Si no tienes turno específico, dejalo null
        asistenciaRepository.save(asistencia);

        // Actualizar estado de la reserva
        reserva.setEstado(Reserva.Estado.ASISTIDA);
        reservaRepository.save(reserva);
    }
}


