package at.ac.ase.postgres.users;

import at.ac.ase.entities.AuctionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuctionHouseRepository extends JpaRepository<AuctionHouse, Long> {
    AuctionHouse findByEmail(String email);

    @Modifying
    @Query("UPDATE AuctionHouse u SET u.passwordHash = :passwordHash WHERE u.email = :email")
    void changePassword(@Param(value = "email")String email,@Param(value = "passwordHash")String passwordHash);

}
