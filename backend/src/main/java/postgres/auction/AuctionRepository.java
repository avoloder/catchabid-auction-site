package postgres.auction;

import entities.AuctionHouse;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<AuctionHouse, Long> {
}
