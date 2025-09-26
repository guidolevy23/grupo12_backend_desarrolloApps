package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.ResultDto;
import com.uade.tpo.gimnasio.dto.auth.LoginResponse;
import com.uade.tpo.gimnasio.dto.auth.RegisterRequest;
import com.uade.tpo.gimnasio.dto.auth.RegisterResponse;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.gimnasio.dto.auth.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/otp")
    public ResponseEntity<ResultDto> validate(@RequestBody OtpDto dto) {
        boolean ok = service.validate(dto);
        if (!ok) {
            return ResponseEntity.status(401)
                    .body(new ResultDto("ERROR", "Invalid OTP"));
        }
        return ResponseEntity.ok(new ResultDto("OK", "User successfully validated"));
    }
}
