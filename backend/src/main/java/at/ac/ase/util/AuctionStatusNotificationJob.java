package at.ac.ase.util;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import at.ac.ase.util.exceptions.EmailNotSentException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class AuctionStatusNotificationJob implements Job {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        AuctionPost auctionPost = (AuctionPost)jobExecutionContext.getJobDetail().getJobDataMap().get("auctionPost");

        for(User user: auctionPost.getSubscriptions()) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("noreply.catchabid@gmail.com");
                message.setTo(user.getEmail());
                message.setSubject("Auction " + auctionPost.getName());
                message.setText("Auction you subscribed for " + auctionPost.getName() + " just started!");
                emailSender.send(message);
            } catch (MailException e) {
                throw new EmailNotSentException();
            }
        }
    }
}
