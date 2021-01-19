package at.ac.ase.controllers;

import at.ac.ase.entities.Notification;
import at.ac.ase.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationWebSocketController {

    @Autowired
    private final SimpMessagingTemplate template;

    @Autowired
    NotificationWebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @Scheduled(fixedRate = 5000)
    public void sendNotification(User user, Notification notification){
        System.out.println("SENDING");
        template.convertAndSend("1/queue/notification", notification);
    }

}
