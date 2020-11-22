package at.ac.ase.service.users;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

public interface AuctionHouseService {

    Optional<AuctionHouse> getAuctionHouseById(Long id);

    AuctionHouse getAuctionHouseByEmail(Map<String,String> userData);

}
