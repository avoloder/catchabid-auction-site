package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.service.auction.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auctions")
@CrossOrigin
public class AuctionController {
    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

    @Autowired
    private AuctionService auctionService;

    @GetMapping("upcoming")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getUpcomingAuctions(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNr) {

        logger.info("Page requested:" + pageSize + " " + pageNr);
        List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNr);
        logger.info("Size of payload for upcoming auctions" + posts.size());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("all")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getAllAuctions(@RequestParam(required = false) Integer pageSize,
                                                        @RequestParam(required = false) Integer pageNr) {
        List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize,pageNr);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("recent")
    public List<AuctionPostDTO> getRecentAuctions(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer auctionsPerPage) {

        return auctionService.getRecentAuctions(pageNumber, auctionsPerPage);
    }
}
