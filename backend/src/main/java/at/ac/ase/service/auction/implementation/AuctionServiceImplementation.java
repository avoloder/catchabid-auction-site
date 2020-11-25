package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.postgres.auction.AuctionRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import at.ac.ase.service.auction.AuctionService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuctionServiceImplementation implements AuctionService {
    @Autowired
    AuctionRepository repository;

    @Override
    public List<AuctionPostDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr){
        return convertAuctionsToDTO(repository.findAllByStartTimeGreaterThan(LocalDateTime.now(), getPageForFutureAuctions(auctionsPerPage,pageNr)));
    }


    @Override
    public List<AuctionPostDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr) {
        Page<AuctionPost> result = repository.findAll(getPageForFutureAuctions(auctionsPerPage,pageNr));
        return convertAuctionsToDTO(result.getContent());
    }

    private List<AuctionPostDTO> convertAuctionsToDTO(Collection<AuctionPost> auctions){
        List<AuctionPostDTO> auctionPostDTOS = new ArrayList<>();
        auctions.forEach((x)-> {
            try {
                auctionPostDTOS.add(convertAuctionToDTO(x));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return auctionPostDTOS;

    }

    private AuctionPostDTO convertAuctionToDTO(AuctionPost auction) throws IOException {
        AuctionPostDTO auctionPostDTO = new AuctionPostDTO();
        auctionPostDTO.setId(auction.getId());
        auctionPostDTO.setAuctionName(auction.getName());
        if (auction.getCategory()!=null) {
            auctionPostDTO.setCategory(auction.getCategory().name());
        }
        if (auction.getCreator()!=null) {
            auctionPostDTO.setCreatorId(auction.getCreator().getId());
            auctionPostDTO.setCreatorName(auction.getCreator().getName());
        }
        auctionPostDTO.setAuctionDescription(auction.getDescription());
        auctionPostDTO.setEndTime(auction.getEndTime());
        auctionPostDTO.setStartTime(auction.getStartTime());
        if (auction.getHighestBid()!=null) {
            auctionPostDTO.setHighestBid(auction.getHighestBid().getOffer());
        }
        auctionPostDTO.setMinPrice(auction.getMinPrice());
        auctionPostDTO.setLocation(auction.getLocation());
        if (auction.getStatus()!=null)
            auctionPostDTO.setStatus(auction.getStatus().name());
        byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\Marija\\Desktop\\WS20\\ASE\\20ws_ase-pr_qse_06\\backend\\src\\main\\resources\\catchabid-logo.png"));
        auctionPostDTO.setImage(Base64.getEncoder().encodeToString(fileContent));
        return auctionPostDTO;
    }

    private Pageable getPageForFutureAuctions(Integer auctionsPerPage  , Integer pageNr){

        if (pageNr != null && auctionsPerPage != null && pageNr >= 0 && auctionsPerPage > 0)
        {
            return PageRequest.of(pageNr, auctionsPerPage, Sort.by("startTime").ascending());
        }
        else {
            return PageRequest.of(0, 50, Sort.by("startTime").ascending());
        }
    }
}
