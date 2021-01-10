package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionQueryDTO;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.repository.auction.AuctionPostQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionDtoTranslator {

    @Autowired
    private ModelMapper modelMapper;

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
        auctionPostSendDTO.setDescription(auction.getDescription());
        auctionPostSendDTO.setAddress(auction.getAddress().getStreet());
        auctionPostSendDTO.setHouseNr(auction.getAddress().getHouseNr());
        auctionPostSendDTO.setCity(auction.getAddress().getCity());
        auctionPostSendDTO.setCountry(auction.getAddress().getCountry());
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
        auctionPostSendDTO.setPaid(auction.getPayment() != null);
        return auctionPostSendDTO;
    }

    public List<AuctionPostSendDTO> toDtoList(List<AuctionPost> auctions) {
        return auctions.stream().map(this::toSendDto).collect(Collectors.toList());
    }

    public AuctionPostQuery toEntity(AuctionQueryDTO queryDTO) {
        return modelMapper.map(queryDTO, AuctionPostQuery.class);
    }
}
