package at.ac.ase.service.auction.implementation;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImplementation implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public List<AuctionPost> getRecentAuctions(Integer pageNr, Integer auctionsPerPage) {
        PageRequest pageRequest = null;

        if (pageNr != null && auctionsPerPage != null && pageNr >= 0 && auctionsPerPage > 0)
        {
            pageRequest = PageRequest.of(pageNr, auctionsPerPage, Sort.by("startTime").descending());
        }
        else {
            pageRequest = PageRequest.of(0, 50, Sort.by("startTime").descending());
        }
        Page<AuctionPost> recentAuctions = auctionRepository.findAll(pageRequest);
        return recentAuctions.getContent();
    }
}
