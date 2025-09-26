package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.ResultDto;
import com.uade.tpo.gimnasio.dto.auth.*;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/otp/validate")
    public ResponseEntity<ResultDto> validate(@RequestBody OtpDto dto) {
        boolean ok = service.validate(dto);
        if (!ok) {
            return ResponseEntity.status(403)
                    .body(new ResultDto("ERROR", "Invalid OTP"));
        }
        return ResponseEntity.ok(new ResultDto("OK", "User successfully validated"));
    }

    @PostMapping("/otp/request")
    public ResponseEntity<String> requestOtp(@RequestBody OtpRequestDTO dto) {
        service.requestOtp(dto.email());
        return ResponseEntity.ok("OTP sent to " + dto.email());
    }
}
