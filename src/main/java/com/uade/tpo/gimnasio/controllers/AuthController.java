package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.auth.LoginResponse;
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
        LoginResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
