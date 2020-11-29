package at.ac.ase.service.auction;


import java.util.List;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import java.util.List;
import java.util.Optional;

public interface AuctionService {

    /**
     * Returns recent pagable auction posts ordered by start_time descending
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @return
     */
    List<AuctionPostSendDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage);

    List<AuctionPostSendDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);

    List<AuctionPostSendDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);

    List<AuctionPost> getAllAuctions();

    AuctionPost createAuction(AuctionPost auctionPost);

    Optional<AuctionPost> getAuctionPost(Long id);

    AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO);

}
