package com.uade.tpo.gimnasio.services;

import java.time.Instant;
import java.util.List;

import com.uade.tpo.gimnasio.models.entity.Asistencia;


public interface AsistenciaService {
  Asistencia registrarAsistencia(Asistencia asistencia);
  List<Asistencia> historialPorUsuario(Long usuarioId);
  List<Asistencia> historialPorUsuarioConFiltros(Long usuarioId, Instant fechaInicio, Instant fechaFin);
}
