package at.ac.ase.redis.repository;

import at.ac.ase.redis.model.HighestBid;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisHighestBidRepository {

    private static final String HIGHEST_BID_CACHE_KEY = "HIGHEST_BID";

    private HashOperations hashOperations;

    private RedisTemplate<Long, HighestBid> redisTemplate;

    public RedisHighestBidRepository(RedisTemplate<Long, HighestBid> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void saveHighestBid(Long auctionId, HighestBid highestBid) {
        hashOperations.put(HIGHEST_BID_CACHE_KEY, auctionId, highestBid);
    }

    public HighestBid getHighestBid(Long auctionId) {
        return (HighestBid) hashOperations.get(HIGHEST_BID_CACHE_KEY, auctionId);
    }

    public void delete(Long auctionId){
        hashOperations.delete(HIGHEST_BID_CACHE_KEY, auctionId);
    }

    

}
