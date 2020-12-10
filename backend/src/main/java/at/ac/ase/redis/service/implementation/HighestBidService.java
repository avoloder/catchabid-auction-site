package at.ac.ase.redis.service.implementation;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Bid;
import at.ac.ase.redis.model.HighestBid;
import at.ac.ase.redis.repository.RedisHighestBidRepository;
import at.ac.ase.redis.service.IHighestBidService;
import at.ac.ase.util.exceptions.NotHighestBidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HighestBidService implements IHighestBidService {

    @Autowired
    private RedisHighestBidRepository highestBidRepository;

    @Override
    public void updateHighestBid(Bid bid) {
        HighestBid currentHighestBid = getHighestBid(bid.getAuction());
        if (currentHighestBid != null && currentHighestBid.getOffer() >= bid.getOffer()) {
                throw new NotHighestBidException(currentHighestBid.getOffer().toString());
        }
        HighestBid newHighestBid = new HighestBid();
        newHighestBid.setOffer(bid.getOffer());
        newHighestBid.setUserId(bid.getUser().getId());

        if (currentHighestBid != null) {
            System.out.println(currentHighestBid.getOffer());
        }
        highestBidRepository.saveHighestBid(bid.getAuction().getId(), newHighestBid);


    }

    @Override
    public HighestBid getHighestBid(AuctionPost auctionPost) {
        return highestBidRepository.getHighestBid(auctionPost.getId());
    }
}