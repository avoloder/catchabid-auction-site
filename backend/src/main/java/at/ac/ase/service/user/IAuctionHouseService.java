package at.ac.ase.service.user;

import at.ac.ase.entities.AuctionHouse;

import java.util.List;
import java.util.Optional;

public interface IAuctionHouseService {

    /**
     * Method which retrieves an auction house based on its id
     * @param id of an auction house
     * @return auction house if found
     */
    Optional<AuctionHouse> getAuctionHouseById(Long id);

    /**
     * Method which retrieves all auction houses from the database
     * @return list of auction houses
     */
    List<AuctionHouse> getAllHouses();

    /**
     * Method which retrieves a single auction house based on its email
     * @param email of the auction house to be retrieved
     * @return auction house if found
     */
    AuctionHouse getAuctionHouseByEmail(String email);

    void changePassword(String email, String password);

}
