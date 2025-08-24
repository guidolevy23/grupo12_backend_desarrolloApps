package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.reservas.ReservaCreateRequestDTO;
import com.uade.tpo.gimnasio.dto.reservas.ReservaResponseDTO;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva;
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

    public ReservaController(ReservaService reservaService, ModelMapper modelMapper) {
        this.reservaService = reservaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crear(@RequestBody ReservaCreateRequestDTO dto) {
        var reserva = modelMapper.map(dto, Reserva.class);
        var guardada = reservaService.crearReserva(reserva);
        return ResponseEntity.ok(modelMapper.map(guardada, ReservaResponseDTO.class));
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
                .map(r -> modelMapper.map(r, ReservaResponseDTO.class))
                .toList();
    }
}


