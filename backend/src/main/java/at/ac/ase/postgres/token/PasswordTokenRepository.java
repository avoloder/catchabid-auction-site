package at.ac.ase.postgres.token;

import at.ac.ase.entities.PasswordResetToken;
import at.ac.ase.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(int token);
}
