package at.ac.ase.service.users;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;

import at.ac.ase.entities.User;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    RegularUser getUserByEmail(String email);

    void changePassword(String email, String password);
}
