package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Asistencia;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    // Registrar asistencia (ej: al escanear QR en el molinete)
    @PostMapping
    public ResponseEntity<Asistencia> registrar(@RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(asistenciaService.registrarAsistencia(asistencia));
    }

    // Historial por usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Asistencia> historial(@PathVariable Long usuarioId) {
        return asistenciaService.historialPorUsuario(usuarioId);
    }
}

