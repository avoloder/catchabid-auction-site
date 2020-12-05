package at.ac.ase.controllers;

import at.ac.ase.dto.BidDTO;
import at.ac.ase.entities.Bid;
import at.ac.ase.entities.User;
import at.ac.ase.service.auction.BidService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bids")
public class BidController {


    @Autowired
    private BidService bidService;


    @PostMapping
    public ResponseEntity<Object> placeBid(
        @RequestBody @Valid BidDTO bidDTO,
        @CurrentSecurityContext(expression = "authentication.principal") User user) {
        Bid bid = bidService.toBid(bidDTO, user);
        return ResponseEntity.ok(bidService.placeBid(bid));
    }



}
