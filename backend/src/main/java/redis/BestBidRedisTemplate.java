package redis;

import entities.Bid;
import org.springframework.data.redis.core.RedisTemplate;

public class BestBidRedisTemplate extends RedisTemplate {
    private Long id;
    private Bid bid;

    public BestBidRedisTemplate() {

    }

    public BestBidRedisTemplate(Bid bid) {
        this.id = bid.getAuctionId();
        this.bid = bid;
    }

    public void setValues(Bid bid) {
        this.bid = bid;
        this.id = bid.getAuctionId();
    }

}
