package at.ac.ase.controllers;

import at.ac.ase.entities.Notification;
import at.ac.ase.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationWebSocketController {

    @Autowired
    private final SimpMessagingTemplate template;

    @Autowired private SimpUserRegistry userRegistry;


    @Autowired
    NotificationWebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    public void sendNotification(User user, Notification notification){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(userRegistry.getUsers().size());

        printConnectedUsers();

        System.out.println(user.getEmail());

        template.convertAndSend("/topic/notification/" + user.getEmail(), notification);
    }

    public void printConnectedUsers() {
        userRegistry.getUsers().stream()
            .map(u -> u.getName())
            .forEach(System.out::println);
    }


}
