package com.crm.pfe.services;

import com.crm.pfe.entities.Notification;
import com.crm.pfe.entities.User;
import com.crm.pfe.repository.NotificationRepository;
import com.crm.pfe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    public final NotificationRepository notificationRepository;
    public final UserRepository userRepository;

    @Override
    public Notification createNotification(Long id, Notification notification) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(()->new RuntimeException("notif not found"));
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        return notificationRepository.findById(id).map(n -> {
            n.setDescription(notification.getDescription());
            n.setStatus(notification.getStatus());
            return notificationRepository.save(n);
        }).orElseThrow(()->new RuntimeException("notif not found"));
    }

}
