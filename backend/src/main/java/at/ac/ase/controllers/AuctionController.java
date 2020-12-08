package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.User;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.user.implementation.AuctionHouseService;
import at.ac.ase.util.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auctions")

public class AuctionController {
    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @Autowired
    private IAuctionService auctionService;
    @Autowired
    private AuctionHouseService auctionHouseService;


    @GetMapping("upcoming")
    public @ResponseBody
    ResponseEntity<List<AuctionPostSendDTO>> getUpcomingAuctions(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Long user) {
        logger.info("Upcoming auctions requested for page size " + pageSize + " and age number requested " + pageNumber);
        if (user != null) {
            List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNumber);
            logger.info("Size of payload for upcoming auctions:" + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctionsForUser(pageSize, pageNumber, user);
            logger.info("Size of payload for upcoming auctions:" + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    @GetMapping("all")
    public @ResponseBody
    ResponseEntity<List<AuctionPostSendDTO>> getAllAuctions(@RequestParam(required = false) Integer pageSize,
                                                            @RequestParam(required = false) Integer pageNr) {
        logger.info("All auctions requested for page size " + pageSize + " and age number requested" + pageNr);
        List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNr);
        logger.info("Size of payload for all auctions: " + posts.size());
        logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("recent")
    public List<AuctionPostSendDTO> getRecentAuctions(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer auctionsPerPage,
            @RequestParam(required = false) Long user) {

        logger.info("Recent auctions requested for page size " + auctionsPerPage + "and age number requested " + pageNumber);
        if (user != null) {
            return auctionService.getRecentAuctions(pageNumber, auctionsPerPage);
        } else {
            List<AuctionPostSendDTO> posts = auctionService.getRecentAuctionsForUser(pageNumber, auctionsPerPage, user);
            logger.info("Size of payload of recent auctions for userId" + user + " is " + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return posts;
        }
    }

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

    @GetMapping("getCategories")
    public ResponseEntity getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(this.auctionService.getCategories());
    }


}
