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

    public void sendNotification(User user, Notification notification){

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        template.convertAndSendToUser(user.getEmail(),"/queue/notification", notification);
    }

}
