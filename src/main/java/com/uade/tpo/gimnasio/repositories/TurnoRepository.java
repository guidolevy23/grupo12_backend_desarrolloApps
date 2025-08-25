package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // para catálogo simple (podés combinar estos filtros en el service)
    List<Turno> findByInicioBetweenOrderByInicioAsc(Instant desde, Instant hasta);
    List<Turno> findByClase_SedeAndInicioBetweenOrderByInicioAsc(Sede sede, Instant desde, Instant hasta);
    List<Turno> findByClase_DisciplinaAndInicioBetweenOrderByInicioAsc(Disciplina disciplina, Instant desde, Instant hasta);
    List<Turno> findByClase_SedeAndClase_DisciplinaAndInicioBetweenOrderByInicioAsc(
            Sede sede, Disciplina disciplina, Instant desde, Instant hasta);
}
