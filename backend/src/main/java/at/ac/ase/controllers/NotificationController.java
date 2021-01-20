package at.ac.ase.controllers;

import at.ac.ase.dto.translator.NotificationDTOTranslator;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.RegularUserNotification;
import at.ac.ase.entities.User;
import at.ac.ase.service.notification.INotificationService;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private NotificationDTOTranslator notificationDTOTranslator;

    @Autowired
    private NotificationWebSocketController webSocketController;

    @GetMapping
    public ResponseEntity<Object> getNotificationsForRegularUser(
        @CurrentSecurityContext(expression = "authentication.principal") User user) {

        RegularUserNotification notification = new RegularUserNotification();
        notification.setReceiver((RegularUser) user);
        notification.setInfo("Your bid has won the auction 'Camera Nixon 395!'");
        notification.setSeen(false);
        webSocketController.sendNotification(user, notification);
        // notificationService.saveNotification(notification);

        return ResponseEntity.ok(
            notificationDTOTranslator.toDtoList(notificationService.getNotificationsForUser(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNotificationSeen(
        @PathVariable Long id) {
        notificationService.updateNotificationSeen(notificationService.getNotificationById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

}
