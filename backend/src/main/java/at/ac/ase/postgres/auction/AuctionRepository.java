package at.ac.ase.postgres.auction;

import at.ac.ase.entities.AuctionPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends CrudRepository<AuctionPost, Long> {
}
