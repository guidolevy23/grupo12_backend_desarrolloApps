package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import com.uade.tpo.gimnasio.repositories.ClaseRepository;
import com.uade.tpo.gimnasio.services.ClaseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;

    public ClaseServiceImpl(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }


    @Override
    public List<Clase> listarClases() {
        return claseRepository.findAll();
    }

    @Override
    public Optional<Clase> obtenerClase(Long id) {
        return claseRepository.findById(id);
    }

    @Override
    public List<Clase> filtrarClases(String sede, String disciplina) {
        return claseRepository.findBySedeAndDisciplina(sede, disciplina);
    }
}

