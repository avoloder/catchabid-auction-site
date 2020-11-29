package at.ac.ase.repository.token;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.PasswordResetToken;
import at.ac.ase.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query("SELECT t FROM PasswordResetToken t WHERE t.token = :token AND ((:user is null and t.user is null) or t.user = :user) AND ((:auctionHouse is null and t.auctionHouse is null) or t.auctionHouse = :auctionHouse) AND t.expiryDate > CURRENT_DATE")
    PasswordResetToken findByToken(@Param(value = "user")RegularUser user, @Param(value = "auctionHouse") AuctionHouse auctionHouse, @Param(value = "token")int token);

    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE ((:user is null and t.user is null) or t.user = :user) AND ((:auctionHouse is null and t.auctionHouse is null) or t.auctionHouse = :auctionHouse)")
    void deleteTokenByUserId(@Param(value = "user")RegularUser user, @Param(value = "auctionHouse") AuctionHouse auctionHouse);
}
