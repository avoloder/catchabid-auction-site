package at.ac.ase.dto.translator;

import at.ac.ase.dto.RegularUserDTO;
import at.ac.ase.entities.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoTranslator {

    @Autowired
    private AddressDtoTranslator addressDtoTranslator;

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
        regularUserDTO.setOwnedAuctions(regularUser.getOwnedAuctions());
        regularUserDTO.setRatings(regularUser.getRatings());
        return regularUserDTO;
    }
}
