package at.ac.ase.postgres.users;

import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<G extends User> extends JpaRepository<G, Long> {
    RegularUser findByEmail(String email);
}
