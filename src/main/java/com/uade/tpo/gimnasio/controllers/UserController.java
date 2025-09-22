package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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