package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.perfil.PerfilResponseDTO;
import com.uade.tpo.gimnasio.dto.perfil.PerfilUpdateRequestDTO;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Usuario;
import com.uade.tpo.gimnasio.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    public UsuarioController(UsuarioService usuarioService, ModelMapper modelMapper) {
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
    }

    // Obtener datos del perfil de un usuario
    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> getUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(modelMapper.map(usuario, PerfilResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar perfil de un usuario
    @PutMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> updateUsuario(
            @PathVariable Long id,
            @RequestBody PerfilUpdateRequestDTO dto
    ) {
        var usuarioActualizado = usuarioService.actualizarPerfil(id, modelMapper.map(dto, Usuario.class));
        return ResponseEntity.ok(modelMapper.map(usuarioActualizado, PerfilResponseDTO.class));
    }
}



