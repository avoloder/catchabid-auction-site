package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.entities.AuctionPost;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionDtoTranslator {

    public AuctionPostSendDTO toSendDto(AuctionPost auction) {

        AuctionPostSendDTO auctionPostSendDTO = new AuctionPostSendDTO();
        auctionPostSendDTO.setId(auction.getId());
        auctionPostSendDTO.setAuctionName(auction.getName());
        if (auction.getCategory()!=null) {
            auctionPostSendDTO.setCategory(auction.getCategory().name());
        }
        if (auction.getCreator()!=null) {
            auctionPostSendDTO.setCreatorId(auction.getCreator().getId());
            auctionPostSendDTO.setCreatorName(auction.getCreator().getName());
        }
        auctionPostSendDTO.setAuctionDescription(auction.getDescription());
        auctionPostSendDTO.setEndTime(auction.getEndTime());
        auctionPostSendDTO.setStartTime(auction.getStartTime());
        if (auction.getHighestBid()!=null) {
            auctionPostSendDTO.setHighestBid(auction.getHighestBid().getOffer());
        }
        auctionPostSendDTO.setMinPrice(auction.getMinPrice());
        if (auction.getStatus()!=null) {
            auctionPostSendDTO.setStatus(auction.getStatus().name());
        }
        auctionPostSendDTO.setImage(Base64.getEncoder().encodeToString(auction.getImage()));
        return auctionPostSendDTO;
    }

    public List<AuctionPostSendDTO> toDtoList(List<AuctionPost> auctions) {
        return auctions.stream().map(this::toSendDto).collect(Collectors.toList());
    }
}
