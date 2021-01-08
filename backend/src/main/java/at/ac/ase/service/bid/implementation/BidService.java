package at.ac.ase.service.bid.implementation;

import at.ac.ase.dto.BidDTO;
import at.ac.ase.dto.translator.BidDtoTranslator;
import at.ac.ase.entities.Bid;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.Status;
import at.ac.ase.entities.User;
import at.ac.ase.redis.service.IHighestBidService;
import at.ac.ase.repository.bid.BidRepository;
import at.ac.ase.util.exceptions.AuctionCancelledException;
import at.ac.ase.util.exceptions.AuctionExpiredException;
import at.ac.ase.util.exceptions.InvalidBidException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.ase.service.bid.IBidService;

@Service
public class BidService implements IBidService {

    @Autowired
    private BidDtoTranslator bidDtoTranslator;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private IHighestBidService highestBidService;


    @Override
    public Bid toBid(BidDTO bidDTO, User user) {
        Bid bid = bidDtoTranslator.toBid(bidDTO);
        bid.setUser((RegularUser) user);
        return bid;
    }

    @Override
    public BidDTO toBidDTO(Bid bid) {
        return bidDtoTranslator.toBidDTO(bid);
    }

    @Override
    public Bid placeBid(Bid bid) {
        if (bid.getAuction().getEndTime().isBefore(LocalDateTime.now())) {
            throw new AuctionExpiredException();
        }
        if (bid.getAuction().getMinPrice() > bid.getOffer()) {
            throw new InvalidBidException();
        }
        if (bid.getAuction().getStatus().equals(Status.CANCELLED)) {
            throw new AuctionCancelledException();
        }
        highestBidService.updateHighestBid(bid);
        bid.getAuction().setHighestBid(bid);
        return bidRepository.save(bid);
    }

}
