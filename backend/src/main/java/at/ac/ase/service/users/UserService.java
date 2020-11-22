package at.ac.ase.service.users;

import at.ac.ase.entities.RegularUser;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    RegularUser getUserByEmail(Map<String,String> userData);
}
