package at.ac.ase.dto.translator;

import at.ac.ase.dto.RegularUserDTO;
import at.ac.ase.entities.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoTranslator {

    @Autowired
    private AddressDtoTranslator addressDtoTranslator;

    @Autowired
    private RatingDtoTranslator ratingDtoTranslator;

    public RegularUserDTO toRegularUserDTO(RegularUser regularUser){
        RegularUserDTO regularUserDTO = new RegularUserDTO();
        regularUserDTO.setId(regularUser.getId());
        regularUserDTO.setEmail(regularUser.getEmail());
        regularUserDTO.setAddress(addressDtoTranslator.toAddressDTO(regularUser.getAddress()));
        regularUserDTO.setActive(regularUser.getActive());
        regularUserDTO.setFirstName(regularUser.getFirstName());
        regularUserDTO.setLastName(regularUser.getLastName());
        regularUserDTO.setPasswordHash(regularUser.getPasswordHash());
        regularUserDTO.setPhoneNr(regularUser.getPhoneNr());
        regularUserDTO.setAuctionSubscriptions(regularUser.getAuctionSubscriptions());
        regularUserDTO.setOwnedAuctions(regularUser.getOwnedAuctions());
        regularUserDTO.setRatings(ratingDtoTranslator.toDtoSet(regularUser.getRatings()));
        return regularUserDTO;
    }
}
