package at.ac.ase.service.auth.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.postgres.users.UserRepository;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.util.exception.TokenUtil;
import at.ac.ase.util.exception.exceptionhandler.AuthorizationException;
import at.ac.ase.util.exception.exceptionhandler.TokenGenerationException;
import at.ac.ase.util.exception.exceptionhandler.UserNotFoundException;
import com.nimbusds.jose.JOSEException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public JSONObject authenticate(Map<String,String> userData) {
        String email = userData.get("email");
        String password = userData.get("password");
        AuctionHouse house = auctionHouseRepository.findByEmail(email);
        RegularUser user = userRepository.findByEmail(email);
        if(house != null) {
            if(passwordEncoder.matches(password, house.getPasswordHash())){
                try{
                    JSONObject token = new JSONObject();
                    token.appendField("token",TokenUtil.generateToken(email, password));
                    return token;
                } catch (JOSEException e){
                    throw new TokenGenerationException();
                }
            } else {
                throw new AuthorizationException();
            }
        }
        if(user != null) {
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                try {
                    JSONObject token = new JSONObject();
                    token.appendField("token", TokenUtil.generateToken(email, password));
                    return token;
                } catch (JOSEException e) {
                    throw new TokenGenerationException();
                }
            } else {
                throw new AuthorizationException();
            }
        }
        throw new UserNotFoundException();
    }

}
