package com.crm.pfe.controllers;

import com.crm.pfe.entities.Notification;
import com.crm.pfe.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notif")
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @PostMapping("/create/{idUser}")
    public Notification createNotification(@PathVariable Long idUser, @RequestBody Notification notification) {
        return notificationService.createNotification(idUser, notification);
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PutMapping("/update/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id,notification);
    }
}
