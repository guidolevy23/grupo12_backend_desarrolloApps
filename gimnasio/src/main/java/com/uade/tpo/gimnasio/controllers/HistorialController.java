package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.historial.AsistenciaResponseDTO;
import com.uade.tpo.gimnasio.dto.historial.HistorialFilterRequestDTO;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final AsistenciaService asistenciaService;
    private final ModelMapper modelMapper;

    public HistorialController(AsistenciaService asistenciaService, ModelMapper modelMapper) {
        this.asistenciaService = asistenciaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/filtrar")
    public List<AsistenciaResponseDTO> historial(@RequestBody HistorialFilterRequestDTO filtro) {
        return asistenciaService.historialPorUsuario(filtro.usuarioId())
                .stream()
                .map(a -> modelMapper.map(a, AsistenciaResponseDTO.class))
                .toList();
    }
}

