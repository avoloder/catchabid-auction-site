package at.ac.ase.dto.translator;

import at.ac.ase.dto.AddressDTO;
import at.ac.ase.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoTranslator {

    public AddressDTO toAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setHouseNr(address.getHouseNr());
        addressDTO.setStreet(address.getStreet());
        return addressDTO;
    }

}
