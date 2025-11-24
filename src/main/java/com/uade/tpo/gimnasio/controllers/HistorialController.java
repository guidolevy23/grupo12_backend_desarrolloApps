package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.historial.AsistenciaResponseDTO;
import com.uade.tpo.gimnasio.dto.historial.CalificarAsistenciaRequestDTO;
import com.uade.tpo.gimnasio.dto.historial.HistorialFilterRequestDTO;
import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.services.AsistenciaService;
import jakarta.validation.Valid;
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
@CrossOrigin(origins = "*")  // Permitir CORS para desarrollo
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
        var turno = asistencia.getTurno();
        
        // Calcular duración en minutos desde el turno
        Integer durationMinutes = null;
        if (turno != null && turno.getInicio() != null && turno.getFin() != null) {
            long seconds = turno.getFin().getEpochSecond() - turno.getInicio().getEpochSecond();
            durationMinutes = (int) (seconds / 60);
        }

        return new AsistenciaResponseDTO(
            asistencia.getId(),
            course.getName(),          // nombre del curso
            course.getBranch().getNombre(),        // sede
            fechaFormateada,           // fecha check-in
            durationMinutes,           // duración calculada desde turno
            course.getProfessor(),     // profesor
            asistencia.getRating(),    // rating de la clase
            asistencia.getComment()    // comentario de la clase
        );
    }

    @PutMapping("/{asistenciaId}/calificar")
    public ResponseEntity<AsistenciaResponseDTO> calificarAsistencia(
            @PathVariable Long asistenciaId,
            @Valid @RequestBody CalificarAsistenciaRequestDTO request) {
        
        // Validaciones de entrada
        if (asistenciaId == null || asistenciaId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Llamar al servicio para calificar
            Asistencia asistenciaActualizada = asistenciaService.calificarAsistencia(
                asistenciaId, 
                request.rating(), 
                request.comment()
            );

            // Mapear a DTO y retornar
            AsistenciaResponseDTO responseDTO = mapToResponseDTO(asistenciaActualizada);
            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            // Errores de validación (rating inválido, comentario muy largo)
            System.out.println("Error de validación: " + e.getMessage());
            return ResponseEntity.badRequest().build();
            
        } catch (RuntimeException e) {
            // Si la asistencia no existe
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.notFound().build();
            }
            // Otros errores de runtime
            return ResponseEntity.internalServerError().build();
        }
    }
}
