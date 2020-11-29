package at.ac.ase.postgres.users;

import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<G extends User> extends JpaRepository<G, Long> {
    RegularUser findByEmail(String email);

    @Modifying
    @Query("UPDATE RegularUser u SET u.passwordHash = :passwordHash WHERE u.email = :email")
    void changePassword(@Param(value = "email")String email,@Param(value = "passwordHash")String passwordHash);
}
