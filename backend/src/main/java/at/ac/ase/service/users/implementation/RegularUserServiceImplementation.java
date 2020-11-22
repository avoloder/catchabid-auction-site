package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.postgres.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.ase.service.users.UserService;

import java.util.Map;

@Service
public class RegularUserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegularUser getUserByEmail(Map<String, String> userData) {
        String email = userData.get("email");
        return userRepository.findByEmail(email);
    }

    @Override
    public RegularUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
