package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByUsuario_IdAndTurno_Id(Long usuarioId, Long turnoId);

    List<Reserva> findByUsuario_IdAndEstadoInOrderByIdDesc(Long usuarioId, Collection<Estado> estados);

    // para próximas reservas del usuario (ej: estado CONFIRMADA)
    List<Reserva> findByUsuario_IdAndEstadoOrderByIdDesc(Long usuarioId, Estado estado);

    List<Reserva> findByUsuarioId(Long usuarioId);
}