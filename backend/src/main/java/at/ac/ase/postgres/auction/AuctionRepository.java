package at.ac.ase.postgres.auction;

import at.ac.ase.entities.AuctionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionHouse, Long> {
}
