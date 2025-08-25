package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.ResultDto;
import com.uade.tpo.gimnasio.dto.auth.AuthResponseDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpVerifyRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpRequestDTO;
import com.uade.tpo.gimnasio.dto.auth.OtpVerifyRequestDTO;
import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<ResultDto> requestOtp(@RequestBody OtpDto request) {
        boolean result = authService.createAndSendOtp(request);
        ResultDto dto = new ResultDto();
        dto.setStatus(result ? "success" : "failure");
        dto.setMessage((result ? "OTP enviado exitosamente a: " : "Error al enviar OTP: ") + request.getIdentifier());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponseDTO> verifyOtp(@RequestBody OtpVerifyRequestDTO request) {
        // Lógica OTP mockeada
        return ResponseEntity.ok(new AuthResponseDTO("Usuario autenticado con éxito", "fake-jwt-token"));
    }
}





