package at.ac.ase.service.users;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;

import at.ac.ase.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<RegularUser> getAllUsers();

    RegularUser getUserByEmail(String email);
}
