package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Asistencia;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import org.springframework.stereotype.Service;

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
        return asistenciaRepository.findByUsuarioId(usuarioId);
    }
}

