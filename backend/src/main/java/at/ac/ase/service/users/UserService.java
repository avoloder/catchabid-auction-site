package at.ac.ase.service.users;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;

import at.ac.ase.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    /**
     * Method which retrieves all users from the database
     * @return list of regular users
     */
    List<RegularUser> getAllUsers();

    /**
     * Method which retrieves a single user based on his/her email
     * @param email of the user to be retrieved
     * @return regular user if found
     */
    RegularUser getUserByEmail(String email);

}
