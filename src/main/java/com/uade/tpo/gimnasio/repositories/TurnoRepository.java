package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    // 🔹 Turnos dentro de un rango horario
    List<Turno> findByInicioBetweenOrderByInicioAsc(Instant desde, Instant hasta);


    // 🔹 Filtrar por disciplina (name) y rango horario
    List<Turno> findByCourse_NameAndInicioBetweenOrderByInicioAsc(
            String name, Instant desde, Instant hasta);

}
