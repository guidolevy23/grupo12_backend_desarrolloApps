package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.historial.CalificacionCreateRequestDTO;
import com.uade.tpo.gimnasio.dto.historial.CalificacionResponseDTO;
import com.uade.tpo.gimnasio.models.entity.Calificacion;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.CalificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;
    private final UserRepository userRepository;

    public CalificacionController(CalificacionService calificacionService, UserRepository userRepository) {
        this.calificacionService = calificacionService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdate(@RequestBody CalificacionCreateRequestDTO body, Authentication authentication) {
        try {
            var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
            Calificacion c = calificacionService.createOrUpdate(user.getId(), body.asistenciaId(), body.estrellas(), body.comentario());
            var resp = new CalificacionResponseDTO(c.getId(), c.getCourse().getId(), c.getEstrellas(), c.getComentario());
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error inesperado");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<java.util.List<CalificacionResponseDTO>> myRatings(Authentication authentication) {
        try {
            var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
            var list = calificacionService.findByUsuarioId(user.getId());
            var resp = list.stream()
                    .map(c -> new CalificacionResponseDTO(c.getId(), c.getCourse().getId(), c.getEstrellas(), c.getComentario()))
                    .toList();
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
