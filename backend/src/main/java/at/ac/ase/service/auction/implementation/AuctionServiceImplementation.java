package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuctionServiceImplementation implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @Override
    public List<AuctionPostDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage) {
        List<AuctionPost> recentAuctions = auctionRepository.findAllByStartTimeLessThan(LocalDateTime.now(),
                getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").descending()));
        return auctionDtoTranslator.toDtoList(recentAuctions);
    }

    @Override
    public List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr) {
        return convertAuctionsToDTO(auctionRepository.findAllByStartTimeGreaterThan(LocalDateTime.now(),
                getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending())));
    }

    @Override
    public List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr) {
        Page<AuctionPost> result = auctionRepository.findAll(getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending()));
        return convertAuctionsToDTO(result.getContent());
    }

    private List<AuctionPostDTO> convertAuctionsToDTO(Collection<AuctionPost> auctions) {
        List<AuctionPostDTO> auctionPostDTOS = new ArrayList<>();
        auctions.forEach((x) -> auctionPostDTOS.add(auctionDtoTranslator.toDto(x)));
        return auctionPostDTOS;
    }

    private Pageable getPageForFutureAuctions(Integer auctionsPerPage, Integer pageNr, Sort sortMethod) {
        if (pageNr == null || pageNr < 0) {
            pageNr = 0;
        }
        if (auctionsPerPage == null || auctionsPerPage < 1) {
            auctionsPerPage = 50;
        }
        return PageRequest.of(pageNr, auctionsPerPage, sortMethod);
    }
}
