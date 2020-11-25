package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.User;
import at.ac.ase.service.auction.AuctionService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.implementation.AuctionHouseServiceImpl;
import at.ac.ase.util.exception.ObjectNotFoundException;
import javax.validation.Valid;
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


    @GetMapping("/upcoming")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getUpcomingAuctions(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNr) {

        logger.info("Page requested:" + pageSize + " " + pageNr);
        List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNr);
        logger.info("Size of payload for upcoming auctions" + posts.size());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getAllAuctions(@RequestParam(required = false) Integer pageSize,
                                                        @RequestParam(required = false) Integer pageNr) {
        List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize,pageNr);
        return new ResponseEntity<>(posts, HttpStatus.OK);
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
        return ResponseEntity.ok(auctionService.createAuction(user, auction));
    }

}
