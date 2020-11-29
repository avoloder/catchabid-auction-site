package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.postgres.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import at.ac.ase.service.users.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class RegularUserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public RegularUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void changePassword(String email, String password) {
        String passwordHash = encoder.encode(password);
        userRepository.changePassword(email, passwordHash);
    }
}
