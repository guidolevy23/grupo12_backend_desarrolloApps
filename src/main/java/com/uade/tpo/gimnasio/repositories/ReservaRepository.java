package com.uade.tpo.gimnasio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.models.entity.Reserva.Estado;

import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // boolean existsByUsuario_IdAndTurno_Id(Long usuarioId, Long turnoId);

    List<Reserva> findByUsuario_IdAndEstadoInOrderByIdDesc(Long usuarioId, Collection<Estado> estados);

    // para próximas reservas del usuario (ej: estado CONFIRMADA)
    List<Reserva> findByUsuario_IdAndEstadoOrderByIdDesc(Long usuarioId, Estado estado);

    List<Reserva> findByUsuarioId(Long usuarioId);

    List<Reserva> findByCourseId(Long courseId);
    long countByCourseId(Long courseId);
}