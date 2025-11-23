package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.notification.CreateNotificationDto;
import com.uade.tpo.gimnasio.dto.notification.NotificationResponseDTO;
import com.uade.tpo.gimnasio.models.entity.Notification;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.NotificationService;
import com.uade.tpo.gimnasio.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<NotificationResponseDTO>> getMyNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByEmail(userName).orElseThrow();

        List<Notification> notifications = notificationService.getNotificationsByUser(user);
        List<NotificationResponseDTO> notificationDTOs = notifications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
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
        notificationService.createNotification(user, body.message());
        return ResponseEntity.noContent().build();
    }

    private NotificationResponseDTO convertToDto(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.isSeen());
        dto.setUserId(notification.getUser().getId());
        return dto;
    }
}
