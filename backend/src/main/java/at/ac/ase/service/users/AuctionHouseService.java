package at.ac.ase.service.users;

import at.ac.ase.entities.AuctionHouse;
import java.util.Optional;

public interface AuctionHouseService {

    Optional<AuctionHouse> getAuctionHouseById(Long id);

}
