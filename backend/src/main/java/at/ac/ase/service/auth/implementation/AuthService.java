package at.ac.ase.service.auth.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.postgres.users.UserRepository;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.util.exception.TokenUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Authentication service that manages authentication requests from {@link at.ac.ase.controllers.AuthController}
 */
@Service
public class AuthService implements IAuthService {

    @Autowired
    AuctionHouseRepository auctionHouseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String authenticate(Map<String,String> userData) {
        String email = userData.get("email");
        String password = userData.get("password");
        AuctionHouse house = auctionHouseRepository.findByEmail(email);
        RegularUser user = userRepository.findByEmail(email);
        if(house != null || user != null) {
            try {
                return TokenUtil.generateToken(email, password);
            } catch (JOSEException e) {
                return null;
            }
        }
        return null;
    }

}
