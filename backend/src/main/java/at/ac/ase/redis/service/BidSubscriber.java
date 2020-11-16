package at.ac.ase.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidSubscriber implements MessageListener {
    private static final List<String> messageList = new ArrayList();

    public static List<String> getMessageList() {
        return messageList;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

    }
}
