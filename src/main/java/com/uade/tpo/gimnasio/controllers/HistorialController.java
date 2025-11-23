package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.historial.AsistenciaResponseDTO;
import com.uade.tpo.gimnasio.dto.historial.HistorialFilterRequestDTO;
import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final AsistenciaService asistenciaService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public HistorialController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<AsistenciaResponseDTO>> obtenerHistorial(@PathVariable Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        List<AsistenciaResponseDTO> historial = asistenciaService.historialPorUsuario(usuarioId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        return ResponseEntity.ok(historial);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<AsistenciaResponseDTO>> historialFiltrado(@RequestBody HistorialFilterRequestDTO filtro) {
        if (filtro.usuarioId() == null || filtro.usuarioId() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Instant fechaInicio = null;
        Instant fechaFin = null;

        try {
            if (filtro.fechaInicio() != null && !filtro.fechaInicio().isEmpty()) {
                fechaInicio = Instant.parse(filtro.fechaInicio());
            }
            if (filtro.fechaFin() != null && !filtro.fechaFin().isEmpty()) {
                fechaFin = Instant.parse(filtro.fechaFin());
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }

        List<AsistenciaResponseDTO> historial = asistenciaService
                .historialPorUsuarioConFiltros(filtro.usuarioId(), fechaInicio, fechaFin)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        return ResponseEntity.ok(historial);
    }

    /** 🔄 Mapea entidad → DTO adaptado a Course */
    private AsistenciaResponseDTO mapToResponseDTO(Asistencia asistencia) {
        LocalDateTime fechaLocal = LocalDateTime.ofInstant(asistencia.getCheckInAt(), ZoneId.systemDefault());
        String fechaFormateada = fechaLocal.format(DATE_FORMATTER);

        var course = asistencia.getCourse();

        return new AsistenciaResponseDTO(
            asistencia.getId(),
            course.getName(),          // nombre del curso
            course.getBranch().getNombre(),        // sede
            fechaFormateada,           // fecha check-in
            null,                      // duración (agregá si existe en Course)
            course.getProfessor()      // profesor
        );
    }
}
