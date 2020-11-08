package redis.service;

import entities.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class BidPublisher {
    @Autowired
    private final RedisTemplate<Long, Bid> bidRedisTemplate;
    @Autowired
    private final ChannelTopic topic;

    public BidPublisher(RedisTemplate<Long, Bid> bidRedisTemplate, ChannelTopic topic) {
        this.bidRedisTemplate = bidRedisTemplate;
        this.topic = topic;
    }

    public void publish(final Bid message) {
        bidRedisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
