package at.ac.ase.dto.translator;

import at.ac.ase.dto.BidDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Bid;
import at.ac.ase.service.auction.AuctionService;
import at.ac.ase.util.exception.ObjectNotFoundException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidDtoTranslator {

    @Autowired
    private AuctionService auctionService;

    public Bid toBid(BidDTO bidDTO) {
        Bid bid = new Bid();
        bid.setOffer(bidDTO.getOffer());
        bid.setDateTime(LocalDateTime.now());

        AuctionPost auction = auctionService.getAuctionPost(bidDTO.getAuctionId())
            .orElseThrow(ObjectNotFoundException::new);
        bid.setAuction(auction);


        return bid;
    }

}
