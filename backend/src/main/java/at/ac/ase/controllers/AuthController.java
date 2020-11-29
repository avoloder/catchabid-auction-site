package at.ac.ase.controllers;

import at.ac.ase.entities.*;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.service.auth.IRegisterService;
import at.ac.ase.service.passwordtoken.implementation.PasswordTokenService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegularUser regularUser){
        RegularUser user = registerService.registerUser(regularUser);
        return user != null ? ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/registerHouse", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody AuctionHouse auctionHouse){
        AuctionHouse user = registerService.registerHouse(auctionHouse);
        return user != null ? ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Map<String,String> userData){
        JSONObject token = authService.authenticate(userData);
        return token != null ? ResponseEntity.status(HttpStatus.OK).body(token) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/requestPasswordReset", method = RequestMethod.POST)
    public ResponseEntity requestPasswordReset(@RequestBody String email){
        boolean emailSent;
        RegularUser user = userService.getUserByEmail(email);
        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail(email);
        if(user == null && auctionHouse == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(user != null) {
            emailSent = authService.sendPasswordResetToken(user);
        }else{
            emailSent = authService.sendPasswordResetToken(auctionHouse);
        }
        return emailSent ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/sendResetPasswordToken", method = RequestMethod.POST)
    public ResponseEntity sendResetPasswordToken(@RequestBody int token){
        PasswordResetToken resetToken = tokenService.getPasswordResetTokenByToken(token);
        if(resetToken == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(user != null) {
            userService.changePassword(email, password);
        }else{
            auctionHouseService.changePassword(email, password);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
