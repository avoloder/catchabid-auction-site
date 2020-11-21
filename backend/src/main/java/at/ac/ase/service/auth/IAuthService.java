package at.ac.ase.service.auth;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;

import java.util.Map;

public interface IAuthService {

    public String authenticate(Map<String,String> userData);
}
