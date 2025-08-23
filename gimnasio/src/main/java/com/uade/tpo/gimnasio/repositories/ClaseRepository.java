package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    List<Clase> findBySedeAndDisciplina(String sede, String disciplina);
}
