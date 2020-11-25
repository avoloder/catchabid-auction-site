package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.entities.AuctionPost;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionDtoTranslator {

    public AuctionPostDTO toDto(AuctionPost auction) {

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
        if (auction.getStatus()!=null) {
            auctionPostDTO.setStatus(auction.getStatus().name());
        }
        auctionPostDTO.setImage(Base64.getEncoder().encodeToString(getImageBytes()));
        return auctionPostDTO;
    }

    public List<AuctionPostDTO> toDtoList(List<AuctionPost> auctions) {
        return auctions.stream().map(this::toDto).collect(Collectors.toList());
    }

    public byte[] getImageBytes() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("catchabid-logo.png")) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[10];
    }
}
