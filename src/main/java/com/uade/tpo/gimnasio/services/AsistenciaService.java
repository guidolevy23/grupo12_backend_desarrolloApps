package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Asistencia;
import java.time.Instant;
import java.util.List;


public interface AsistenciaService {
  Asistencia registrarAsistencia(Asistencia asistencia);
  List<Asistencia> historialPorUsuario(Long usuarioId);
  List<Asistencia> historialPorUsuarioConFiltros(Long usuarioId, Instant fechaInicio, Instant fechaFin);
}
