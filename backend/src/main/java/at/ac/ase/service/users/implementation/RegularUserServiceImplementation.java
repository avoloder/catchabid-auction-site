package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.RegularUser;
import at.ac.ase.postgres.users.UserRepository;
import at.ac.ase.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularUserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RegularUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public RegularUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
