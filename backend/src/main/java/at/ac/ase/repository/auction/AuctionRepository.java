package at.ac.ase.repository.auction;

import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Category;
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

    /**
     * get active auctions from database by user preferences
     * @param fromStartTime latest start date
     * @param untilEndTime earliest end time
     * @param categories preferences categories of user
     * @param pageable page of results
     * @return
     */
    List<AuctionPost> findAllByStartTimeLessThanAndEndTimeGreaterThanAndCategoryIn(LocalDateTime fromStartTime, LocalDateTime untilEndTime, List<Category> categories, Pageable pageable);

    /**
     * get upcoming auctions from database by user preferences
     * @param from earliest starting date of results
     * @param pageable page of results
     * @param categories preferences categories of user
     * @return
     */
    List<AuctionPost> findAllByStartTimeGreaterThanAndCategoryIn(LocalDateTime from, List<Category> categories, Pageable pageable);

    /**
     * get countries where auctions exist
     * @return
     */
    @Query(value = "SELECT distinct country FROM auction_post", nativeQuery = true)
    List<String> getAllCountriesWhereAuctionsExist();
}
