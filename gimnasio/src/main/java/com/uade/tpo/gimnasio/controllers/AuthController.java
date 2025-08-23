package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.auth.AuthResponseDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpVerifyRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpVerifyRequestDTO;
import com.uade.tpo.gimnasio.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<AuthResponseDTO> requestOtp(@RequestBody OtpRequestDTO request) {
        // Lógica para generar y enviar OTP (mockeado en esta entrega)
        return ResponseEntity.ok(new AuthResponseDTO("OTP enviado al correo " + request.email(), null));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponseDTO> verifyOtp(@RequestBody OtpVerifyRequestDTO request) {
        // Lógica para validar OTP (mockeado)
        return ResponseEntity.ok(new AuthResponseDTO("Usuario autenticado con éxito", "fake-jwt-token"));
    }
}



