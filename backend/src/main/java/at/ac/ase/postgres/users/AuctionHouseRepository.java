package at.ac.ase.postgres.users;

import at.ac.ase.entities.AuctionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionHouseRepository extends JpaRepository<AuctionHouse, Long> {

}
