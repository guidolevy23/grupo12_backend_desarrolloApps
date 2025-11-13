package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.reservas.ReservaCreateRequestDTO;
import com.uade.tpo.gimnasio.dto.reservas.ReservaResponseDTO;
import com.uade.tpo.gimnasio.models.entity.Course;
import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.services.ReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ModelMapper modelMapper;
    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaService reservaService, ModelMapper modelMapper, ReservaRepository reservaRepository) {
        this.reservaService = reservaService;
        this.modelMapper = modelMapper;
        this.reservaRepository = reservaRepository;
    }

 @PostMapping
public ResponseEntity<ReservaResponseDTO> crear(@RequestBody ReservaCreateRequestDTO dto) {

    Reserva reserva = reservaService.crearReserva(dto.usuarioId(), dto.courseId());

    ReservaResponseDTO response = new ReservaResponseDTO(
            reserva.getId(),
            reserva.getEstado().name(),
            reserva.getCourse().getName(),
            reserva.getCourse().getBranch(),
            reserva.getCourse().getStartsAt() != null ? reserva.getCourse().getStartsAt().toString() : null
    );

    return ResponseEntity.ok(response);
}




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
public List<ReservaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
    return reservaService.listarReservasPorUsuario(usuarioId)
            .stream()
            .map(r -> new ReservaResponseDTO(
                    r.getId(),
                    r.getEstado().name(),
                    r.getCourse().getName(),
                    r.getCourse().getBranch(),
                    r.getCourse().getStartsAt().toString()
            ))
            .toList();
}


    
    // 🔹 Endpoints de prueba
    @GetMapping("/byCourse/{courseId}")
    public List<Reserva> getReservasByCourse(@PathVariable Long courseId) {
        return reservaRepository.findByCourseId(courseId);
    }

    @GetMapping("/countByCourse/{courseId}")
    public long countReservasByCourse(@PathVariable Long courseId) {
        return reservaRepository.countByCourseId(courseId);
    }
}


