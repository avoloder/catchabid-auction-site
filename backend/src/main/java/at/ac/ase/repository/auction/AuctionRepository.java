package at.ac.ase.repository.auction;

import at.ac.ase.entities.AuctionPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionPost, Long> {
    /**
     *
     * @param from
     * @param pageable
     * @return
     */
    List<AuctionPost> findAllByStartTimeGreaterThan(LocalDateTime from, Pageable pageable);

    /**
     *
     * @param fromStartTime
     * @param untilEndTime
     * @param pageable
     * @return
     */
    List<AuctionPost> findAllByStartTimeLessThanAndEndTimeGreaterThan(LocalDateTime fromStartTime, LocalDateTime untilEndTime, Pageable pageable);

}
