package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.ResultDto;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.OtpRepository;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();
        return ResponseEntity.ok(user);
    }
}