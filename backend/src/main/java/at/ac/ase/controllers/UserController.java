package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionHouseDTO;
import at.ac.ase.dto.RegularUserDTO;
import at.ac.ase.dto.translator.AuctionHouseDtoTranslator;
import at.ac.ase.dto.translator.UserDtoTranslator;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IRegularUserService regularUserService;

    @Autowired
    IAuctionHouseService auctionHouseService;

    @Autowired
    UserDtoTranslator userDtoTranslator;

    @Autowired
    AuctionHouseDtoTranslator auctionHouseDtoTranslator;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity findUser(@RequestParam Map<String, String> userData){
        logger.info("Retrieving user with the email " + userData.get("email"));
        String email = userData.get("email");
        RegularUser user = regularUserService.getUserByEmail(email);
        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail(email);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(userDtoTranslator.toRegularUserDTO(user));
        } else if(auctionHouse != null){
            return ResponseEntity.status(HttpStatus.OK).body(auctionHouseDtoTranslator.toAuctionHouseDTO(auctionHouse));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public ResponseEntity<RegularUser> updateUser(@RequestParam String email, @RequestBody RegularUserDTO user){
        logger.info("Updating user with the email " + user.getEmail());
        logger.info("Address Data : " +user.getAddress());
        return ResponseEntity.status(HttpStatus.OK).body(regularUserService.updateUser(email, user));
    }

    @RequestMapping(value="/updateHouse", method = RequestMethod.POST)
    public ResponseEntity updateHouse(@RequestParam String email, @RequestBody AuctionHouseDTO auctionHouse){
        logger.info("Updating auction house with the email " + auctionHouse.getEmail());
        auctionHouseService.updateHouse(email, auctionHouse);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
