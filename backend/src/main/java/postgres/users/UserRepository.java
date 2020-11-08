package postgres.users;

import entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository<G extends User> extends CrudRepository<G, Long> {
}
