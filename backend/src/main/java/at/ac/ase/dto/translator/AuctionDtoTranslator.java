package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.RegularUser;
import org.springframework.stereotype.Component;

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
            if (auction.getCreator() instanceof AuctionHouse) {
                auctionPostSendDTO.setCreatorName(((AuctionHouse) auction.getCreator()).getName());
            } else {
                auctionPostSendDTO.setCreatorName(((RegularUser) auction.getCreator()).getFirstName() + " " +
                        ((RegularUser) auction.getCreator()).getLastName());
            }
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
        if (auction.getImage() != null) {
            auctionPostSendDTO.setImage(Base64.getEncoder().encodeToString(auction.getImage()));
        }
        return auctionPostSendDTO;
    }

    public List<AuctionPostSendDTO> toDtoList(List<AuctionPost> auctions) {
        return auctions.stream().map(this::toSendDto).collect(Collectors.toList());
    }
}
