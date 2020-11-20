package at.ac.ase.service.auth.implementation;

import at.ac.ase.entities.User;
import at.ac.ase.service.auth.IAuthService;
import org.springframework.stereotype.Service;

/**
 * Authentication service that manages authentication requests from {@link at.ac.ase.controllers.LoginController}
 */
@Service
public class AuthService implements IAuthService {


    @Override
    public boolean authenticate(User user) {
        User u = user;
        return true;
    }
}
