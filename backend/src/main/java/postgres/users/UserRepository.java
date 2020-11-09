package postgres.users;

import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<G extends User> extends JpaRepository<G, Long> {
}
