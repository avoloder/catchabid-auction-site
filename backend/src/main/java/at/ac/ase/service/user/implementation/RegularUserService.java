package at.ac.ase.service.user.implementation;

import at.ac.ase.entities.RegularUser;
import at.ac.ase.repository.user.UserRepository;
import at.ac.ase.service.user.IRegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegularUserService implements IRegularUserService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public List<RegularUser> getAllUsers() {
        return userRepository.findAll();
    }

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

    @Override
    public Optional<RegularUser> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
