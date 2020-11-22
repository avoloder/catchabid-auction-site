package at.ac.ase.service.auction;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import java.util.Optional;

public interface AuctionService {

    AuctionPost createAuction(User user, AuctionCreationDTO auctionPost);

    Optional<AuctionPost> getAuctionPost(Long id);

}
