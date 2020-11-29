package at.ac.ase.postgres.users;

import at.ac.ase.entities.AuctionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuctionHouseRepository extends JpaRepository<AuctionHouse, Long> {
    /**
     * Method which retrieves a single auction house on its email
     * @param email of the auction house to be retrieved
     * @return auction house if found
     */
    AuctionHouse findByEmail(String email);
}
