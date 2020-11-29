package at.ac.ase.postgres.users;

import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RegularUser, Long> {

    /**
     * Method which retrieves a single user based on his/her email
     * @param email of the user to be retrieved
     * @return regular user if found
     */
    RegularUser findByEmail(String email);
}
