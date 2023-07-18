package com.crm.pfe.services;

import com.crm.pfe.entities.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Long id,Notification notification);

    Notification getNotificationById(Long id);

    Notification updateNotification(Long id, Notification notification);
}
