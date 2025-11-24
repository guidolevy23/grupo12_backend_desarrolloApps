package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.notification.CreateNotificationDto;
import com.uade.tpo.gimnasio.models.entity.Notification;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public NotificationController(NotificationService notificationService,  UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Notification>> getMyNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByEmail(userName).orElseThrow();
        List<Notification> notifications = notificationService.getNotificationsByUser(user);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateNotificationDto body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByEmail(userName).orElseThrow();
        notificationService.createNotification(user, body.title(), body.body());
        return ResponseEntity.noContent().build();
    }
}
