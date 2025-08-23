package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import com.uade.tpo.gimnasio.models.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    List<Clase> findBySedeAndDisciplina(Sede sede, Disciplina disciplina);

}
