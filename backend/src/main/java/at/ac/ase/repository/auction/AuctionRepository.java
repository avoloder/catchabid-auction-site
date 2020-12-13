package at.ac.ase.repository.auction;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.repository.auction.implementation.AuctionRepositoryCustomQueries;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionPost, Long>, AuctionRepositoryCustomQueries {

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

    @Query(value = "SELECT distinct country FROM auction_post", nativeQuery = true)
    List<String> getAllCountriesWhereAuctionsExist();
}
