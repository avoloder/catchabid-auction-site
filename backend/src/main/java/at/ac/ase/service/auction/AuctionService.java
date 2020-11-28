package at.ac.ase.service.auction;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import java.util.List;
import java.util.Optional;

public interface AuctionService {

    List<AuctionPost> getAllAuctions();

    AuctionPost createAuction(AuctionPost auctionPost);

    Optional<AuctionPost> getAuctionPost(Long id);

    AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO);

}
