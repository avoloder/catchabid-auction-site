package at.ac.ase.dto.translator;

import at.ac.ase.dto.AuctionHouseDTO;
import at.ac.ase.entities.AuctionHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuctionHouseDtoTranslator {

    @Autowired
    private AddressDtoTranslator addressDtoTranslator;

    public AuctionHouseDTO toAuctionHouseDTO(AuctionHouse auctionHouse){
        AuctionHouseDTO auctionHouseDTO = new AuctionHouseDTO();
        auctionHouseDTO.setTid(auctionHouse.getTid());
        auctionHouseDTO.setAddress(addressDtoTranslator.toAddressDTO(auctionHouse.getAddress()));
        auctionHouseDTO.setName(auctionHouse.getName());
        auctionHouseDTO.setId(auctionHouse.getId());
        auctionHouseDTO.setActive(auctionHouse.getActive());
        auctionHouseDTO.setOwnedAuctions(auctionHouse.getOwnedAuctions());
        auctionHouseDTO.setPasswordHash(auctionHouse.getPasswordHash());
        auctionHouseDTO.setPhoneNr(auctionHouse.getPhoneNr());
        auctionHouseDTO.setEmail(auctionHouse.getEmail());
        auctionHouseDTO.setOwnedAuctions(auctionHouse.getOwnedAuctions());
        return auctionHouseDTO;
    }
}
