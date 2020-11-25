package at.ac.ase.service.auction;

import at.ac.ase.dto.AuctionPostDTO;
import java.util.List;

public interface AuctionService {
    List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);
    List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);
 }
