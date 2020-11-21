package at.ac.ase.postgres.auction;

import at.ac.ase.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository<G extends User> extends CrudRepository<G, Long> {
}
