package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseFilterRequestDTO;
import com.uade.tpo.gimnasio.dto.catalogoClases.ClaseResponseDTO;
import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import com.uade.tpo.gimnasio.models.entity.Sede;
import com.uade.tpo.gimnasio.services.ClaseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;
    private final ModelMapper modelMapper;

    public ClaseController(ClaseService claseService, ModelMapper modelMapper) {
        this.claseService = claseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ClaseResponseDTO> listar() {
        return claseService.listarClases()
                .stream()
                .map(clase -> modelMapper.map(clase, ClaseResponseDTO.class))
                .toList();
    }

    @PostMapping("/filtrar")
    public List<ClaseResponseDTO> filtrar(@RequestBody ClaseFilterRequestDTO filtro) {
        return claseService.filtrarClases(filtro.sede(), filtro.disciplina())
                .stream()
                .map(clase -> modelMapper.map(clase, ClaseResponseDTO.class))
                .toList();
    }
}


