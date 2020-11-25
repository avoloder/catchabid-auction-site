package at.ac.ase.service.auction;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.entities.AuctionPost;

import java.util.List;

public interface AuctionService {

    /**
     * Returns recent pagable auction posts ordered by start_time descending
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @return
     */
    List<AuctionPostDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage);

    List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);
    List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);
 }
