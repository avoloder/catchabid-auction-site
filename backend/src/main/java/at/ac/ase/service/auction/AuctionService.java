package at.ac.ase.service.auction;

import at.ac.ase.dto.AuctionPostDTO;
import java.util.List;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import java.util.Optional;

public interface AuctionService {
    List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);
    List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);
 }

    AuctionPost createAuction(User user, AuctionCreationDTO auctionPost);

    Optional<AuctionPost> getAuctionPost(Long id);

}
