package at.ac.ase.postgres.auction;

import at.ac.ase.entities.AuctionPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionPost, Long> {
    List<AuctionPost> findAllByStartTimeGreaterThan(LocalDateTime from, Pageable pageable);
    List<AuctionPost> findAllByStartTimeLessThan(LocalDateTime from, Pageable pageable);
}
