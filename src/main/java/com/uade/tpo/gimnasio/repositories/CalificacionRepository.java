package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    java.util.List<Calificacion> findByUsuario_Id(Long usuarioId);
    Optional<Calificacion> findByUsuario_IdAndCourse_Id(Long usuarioId, Long courseId);
}
