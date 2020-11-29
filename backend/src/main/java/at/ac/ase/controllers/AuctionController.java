package at.ac.ase.controllers;

import at.ac.ase.AuctionApplication;
import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import at.ac.ase.service.auction.AuctionService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.implementation.AuctionHouseServiceImpl;
import at.ac.ase.util.exception.ObjectNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auctions")
public class AuctionController {


    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionHouseServiceImpl auctionHouseService;

    @PostMapping
    public ResponseEntity<Object> createAuction(
        @RequestBody @Valid AuctionCreationDTO auction,
        @CurrentSecurityContext(expression = "authentication.principal") User user) {
        if (auction.getId() != null) {
            auctionService
                .getAuctionPost(auction.getId())
                .orElseThrow(ObjectNotFoundException::new);
        }
        AuctionPost auctionPost = auctionService.toAuctionPostEntity(user, auction);
        return ResponseEntity.ok(auctionService.createAuction(auctionPost));
    }

}
