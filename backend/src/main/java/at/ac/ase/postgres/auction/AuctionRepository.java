package at.ac.ase.postgres.auction;

import at.ac.ase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository<G extends User> extends JpaRepository<G, Long> {
}
