package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.dto.CourseProjection;
import com.uade.tpo.gimnasio.dto.ReservaProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.models.entity.Reserva.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "reservas", path = "reservations", excerptProjection = ReservaProjection.class)
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    // boolean existsByUsuario_IdAndTurno_Id(Long usuarioId, Long turnoId);

    List<Reserva> findByUsuario_IdAndEstadoInOrderByIdDesc(Long usuarioId, Collection<Estado> estados);

    // para próximas reservas del usuario (ej: estado CONFIRMADA)
    List<Reserva> findByUsuario_IdAndEstadoOrderByIdDesc(Long usuarioId, Estado estado);

    @RestResource(path = "byUser", rel = "byUser")
    List<Reserva> findByUsuarioId(@Param("userId") Long usuarioId);

    List<Reserva> findByCourseId(Long courseId);
    long countByCourseId(Long courseId);
}