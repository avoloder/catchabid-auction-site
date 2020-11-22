package at.ac.ase.controllers;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuctionHouseService auctionHouseService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity findUser(@RequestBody Map<String,String> userData){
        RegularUser user = userService.getUserByEmail(userData);
        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail(userData);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else if(auctionHouse != null){
            return ResponseEntity.status(HttpStatus.OK).body(auctionHouse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
