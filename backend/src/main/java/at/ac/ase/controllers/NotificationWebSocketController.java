package at.ac.ase.controllers;

import at.ac.ase.entities.Notification;
import at.ac.ase.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationWebSocketController {

    @Autowired
    private final SimpMessagingTemplate template;


    @Autowired
    NotificationWebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    /**
     * Method to send {@link Notification} to the subscribed {@link User} through a Web socket.
     *
     * @param user {@link User} which should receive the notification.
     * @param notification {@link Notification} that should be sent to the given user.
     */
    public void sendNotification(User user, Notification notification){
        template.convertAndSend("/topic/notification/" + user.getEmail(), notification);
    }

}
