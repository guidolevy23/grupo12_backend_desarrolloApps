package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Notification;
import com.uade.tpo.gimnasio.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndSeenOrderByCreatedAtDesc(User user, boolean seen);
}
