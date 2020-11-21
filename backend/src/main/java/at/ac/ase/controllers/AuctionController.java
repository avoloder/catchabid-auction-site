package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.service.auction.AuctionService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.util.exception.ObjectNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionHouseService auctionHouseService;

    @PostMapping
    public ResponseEntity<Object> createAuction(
        @RequestBody @Valid AuctionCreationDTO auction) {
        if (auction.getId() != null) {
            auctionService
                .getAuctionPost(auction.getId())
                .orElseThrow(ObjectNotFoundException::new);
        }
        auctionHouseService
            .getAuctionHouseById(auction.getCreatorId())
            .orElseThrow(ObjectNotFoundException::new);
        auctionService.createAuction(auction);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
