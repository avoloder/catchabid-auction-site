package at.ac.ase.service.auth;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import net.minidev.json.JSONObject;

import java.util.Map;

public interface IAuthService {

    public JSONObject authenticate(Map<String,String> userData);

    public void sendPasswordResetToken(User user);
}
