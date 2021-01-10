package at.ac.ase.controllers;

import at.ac.ase.dto.RatingDataDTO;
import at.ac.ase.dto.translator.AuctionHouseDtoTranslator;
import at.ac.ase.dto.translator.RatingDtoTranslator;
import at.ac.ase.dto.translator.UserDtoTranslator;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.Rating;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import at.ac.ase.service.user.rating.IRatingService;
import at.ac.ase.util.exceptions.UserAlreadyExistsException;
import at.ac.ase.util.exceptions.UserAlreadyRatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.faces.annotation.RequestMap;
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
    IRatingService ratingService;

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

    @RequestMapping(value = "/setRating", method = RequestMethod.POST)
    public ResponseEntity setRating(
            @RequestBody RatingDataDTO ratingData,
            @CurrentSecurityContext(expression = "authentication.principal") User user){
        logger.info("Setting rating for user");
        try{
            this.ratingService.setRating(ratingData, user);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataAccessException e) {
            throw new UserAlreadyRatedException();
        }
    }

    @RequestMapping(value = "/calculateRating", method = RequestMethod.GET)
    public ResponseEntity calculateRating(@RequestParam Map<String, String> userData){
        logger.info("Calculating rating for the user " + userData.get("email"));
        Double rating = this.ratingService.calculateRating(userData.get("email"));
        return ResponseEntity.status(HttpStatus.OK).body(rating);
    }
}
