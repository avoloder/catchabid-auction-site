package at.ac.ase.redis;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Bid;
import org.springframework.data.redis.core.RedisTemplate;

public class BestBidRedisTemplate extends RedisTemplate {
    private AuctionPost id;
    private Bid bid;

    public BestBidRedisTemplate() {

    }

    public BestBidRedisTemplate(Bid bid) {
        this.id = bid.getAuction();
        this.bid = bid;
    }

    public void setValues(Bid bid) {
        this.bid = bid;
        this.id = bid.getAuction();
    }

}
