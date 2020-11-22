package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionDto;
import at.ac.ase.entities.AuctionPost;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionDtoTranslator {

    public AuctionDto toDto(AuctionPost auction) {
        AuctionDto dto = new AuctionDto();
        dto.setStartTime(auction.getStartTime());
        dto.setEndTime(auction.getEndTime());
        dto.setStatus(auction.getStatus().name());
        dto.setCategory(auction.getCategory().name());

        dto.setCreator(auction.getCreator() != null ? auction.getCreator().getName(): null);
        dto.setHighestBid(auction.getHighestBid() != null ? auction.getHighestBid().getOffer() : null);

        return dto;
    }

    public List<AuctionDto> toDtoList(List<AuctionPost> auctions) {
        return auctions.stream().map(this::toDto).collect(Collectors.toList());
    }
}
