package at.ac.ase.controllers;

import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.service.auth.IRegisterService;
import at.ac.ase.util.exception.TokenUtil;
import com.nimbusds.jose.JOSEException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.text.ParseException;
import java.util.Map;

/**
 * Login controller that declares and implements REST methods and forward these requests to {@link at.ac.ase.service.auth.implementation.AuthService}
 */
@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private IRegisterService registerService;

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


}
