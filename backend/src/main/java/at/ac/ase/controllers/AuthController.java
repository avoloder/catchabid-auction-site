package at.ac.ase.controllers;

import at.ac.ase.entities.*;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.service.auth.IRegisterService;
import at.ac.ase.service.passwordtoken.implementation.PasswordTokenService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import at.ac.ase.util.exception.TokenUtil;
import at.ac.ase.util.exception.exceptionhandler.ResetTokenNotFound;
import at.ac.ase.util.exception.exceptionhandler.UserAlreadyExistsException;
import at.ac.ase.util.exception.exceptionhandler.UserNotFoundException;
import com.nimbusds.jose.JOSEException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Login controller that declares and implements REST methods and forward these requests to {@link at.ac.ase.service.auth.implementation.AuthService}
 */
@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private IRegisterService registerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionHouseService auctionHouseService;
    @Autowired
    private PasswordTokenService tokenService;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegularUser regularUser){
        try {
            RegularUser user = registerService.registerUser(regularUser);
            return user != null ? ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataAccessException e){
            throw new UserAlreadyExistsException();
        }
    }

    @RequestMapping(value = "/registerHouse", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody AuctionHouse auctionHouse){
        try {
            AuctionHouse user = registerService.registerHouse(auctionHouse);
            return user != null ? ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataAccessException e){
            throw new UserAlreadyExistsException();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Map<String,String> userData){
        JSONObject token = authService.authenticate(userData);
        return token != null ? ResponseEntity.status(HttpStatus.OK).body(token) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/requestPasswordReset", method = RequestMethod.POST)
    public ResponseEntity requestPasswordReset(@RequestBody String email){
        RegularUser user = userService.getUserByEmail(email);
        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail(email);
        if(user == null && auctionHouse == null){
            throw new UserNotFoundException();
        }
        if(user != null) {
            authService.sendPasswordResetToken(user);
        }else{
            authService.sendPasswordResetToken(auctionHouse);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/sendResetPasswordToken", method = RequestMethod.POST)
    public ResponseEntity sendResetPasswordToken(@RequestBody Map<String, String> tokenData){
        int token = Integer.parseInt(tokenData.get("token"));
        String email = tokenData.get("email");
        PasswordResetToken resetToken = tokenService.getPasswordResetTokenByToken(email, token);
        if(resetToken == null){
           throw new ResetTokenNotFound();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseEntity resetPassword(@RequestBody Map<String, String> userData ){
        String email = userData.get("email");
        String password = userData.get("password");
        RegularUser user = userService.getUserByEmail(email);
        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail(email);
        if(user == null && auctionHouse == null){
            throw new UserNotFoundException();
        }
        if(user != null) {
            userService.changePassword(email, password);
        }else{
            auctionHouseService.changePassword(email, password);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
