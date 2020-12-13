package at.ac.ase.redis.service;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Bid;
import at.ac.ase.redis.model.HighestBid;

public interface IHighestBidService {

    void updateHighestBid(Bid bid);

    HighestBid getHighestBid(AuctionPost auctionPost);

}
