package at.ac.ase.redis;

import at.ac.ase.entities.Bid;

public interface RedisRepository {

    /**
     * Add key-value pair to Redis.
     */
    void add(Bid movie);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);

    /**
     * find a movie
     */
    Bid findBestBid(String id);

}
