package at.ac.ase.controllers;

import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.service.auth.IAuthService;
import at.ac.ase.service.auth.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Login controller that declares and implements REST methods and forward these requests to {@link at.ac.ase.service.auth.implementation.AuthService}
 */
@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class LoginController {

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

    @RequestMapping(value = "/registerAddress", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Address address){
        Address user = registerService.registerAddress(address);
        return user != null ? ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
