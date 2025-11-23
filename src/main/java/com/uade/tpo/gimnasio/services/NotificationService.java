package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Notification;
import com.uade.tpo.gimnasio.models.entity.User;

import java.util.List;

public interface NotificationService {
    Notification createNotification(User user, String title, String body);
    List<Notification> getNotificationsByUser(User user);
    void markAsRead(Long notificationId);
}
