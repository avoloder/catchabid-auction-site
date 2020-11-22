package at.ac.ase.service.auction;


import java.util.List;
import at.ac.ase.dto.AuctionPostDTO;
import java.util.List;
import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import java.util.Optional;

public interface AuctionService {

    /**
     * Returns recent pagable auction posts ordered by start_time descending
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @return
     */
    List<AuctionPost> getRecentAuctions(Integer pageNr, Integer auctionsPerPage);
    List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);
    List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);
 }

    AuctionPost createAuction(User user, AuctionCreationDTO auctionPost);

    Optional<AuctionPost> getAuctionPost(Long id);

}
