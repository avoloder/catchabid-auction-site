package at.ac.ase.postgres.users;

import at.ac.ase.entities.AuctionHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionHouseRepository extends CrudRepository<AuctionHouse, Long> {
    AuctionHouse findByEmail(String email);
}
