package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> historialPorUsuario(Long usuarioId) {
        return asistenciaRepository.findByUsuario_IdOrderByCheckInAtDesc(usuarioId);
    }

    @Override
    public List<Asistencia> historialPorUsuarioConFiltros(Long usuarioId, Instant fechaInicio, Instant fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return asistenciaRepository.findByUsuario_IdAndCheckInAtBetweenOrderByCheckInAtDesc(usuarioId, fechaInicio, fechaFin);
        } else {
            return historialPorUsuario(usuarioId);
        }
    }

    @Override
    public Asistencia calificarAsistencia(Long asistenciaId, Integer rating, String comment) {
        // Validar que la asistencia existe
        Asistencia asistencia = asistenciaRepository.findById(asistenciaId)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con ID: " + asistenciaId));

        // Validar rating (1-5)
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }

        // Validar y procesar comment
        String comentarioFinal = null;
        if (comment != null) {
            String comentarioTrimmed = comment.trim();
            
            // Si después de trim está vacío, guardamos null
            if (!comentarioTrimmed.isEmpty()) {
                // Validar longitud máxima (500 caracteres)
                if (comentarioTrimmed.length() > 500) {
                    throw new IllegalArgumentException("El comentario no puede superar los 500 caracteres");
                }
                comentarioFinal = comentarioTrimmed;
            }
        }

        // Actualizar la asistencia
        asistencia.setRating(rating);
        asistencia.setComment(comentarioFinal);

        System.out.println("Calificando asistencia ID " + asistenciaId + " con rating " + rating);

        return asistenciaRepository.save(asistencia);
    }
}
