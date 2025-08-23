package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.uade.tpo.gimnasio.models.entity.Sede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClaseService {
  List<Clase> listarClases();
  Optional<Clase> obtenerClase(Long id);
  List<Clase> filtrarClases(Sede sede, Disciplina disciplina);
}
