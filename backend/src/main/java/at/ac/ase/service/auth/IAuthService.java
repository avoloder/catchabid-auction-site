package at.ac.ase.service.auth;

import at.ac.ase.entities.User;

public interface IAuthService {

    public boolean authenticate(User user);
}
