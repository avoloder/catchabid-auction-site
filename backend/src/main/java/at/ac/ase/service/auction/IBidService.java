package at.ac.ase.service.auction;

import at.ac.ase.dto.BidDTO;
import at.ac.ase.entities.Bid;
import at.ac.ase.entities.User;

public interface IBidService {

    /**
     * Method to tranform a {@link BidDTO} object to a {@link Bid}
     * @param bidDTO {@link BidDTO} object to be transformed
     *
     * @return converted {@link Bid}
     */
    Bid toBid(BidDTO bidDTO, User user);

    /**
     * Method to place a {@link Bid} for and auction.
     *
     * @param bid {@link Bid} object to be stored.
     */
    Bid placeBid(Bid bid);

}
