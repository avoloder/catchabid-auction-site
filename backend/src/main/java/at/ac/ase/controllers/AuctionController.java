package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionPostDTO;
import at.ac.ase.service.auction.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/auctions")
public class AuctionController {
    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @Autowired
    private AuctionService auctionService;

    @GetMapping("upcoming")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getUpcomingAuctions(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNr) {

        logger.info("Upcoming auctions requested for page size "+pageSize+" and age number requested " + pageNr);
        try {
            List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNr);
            logger.info("Size of payload for upcoming auctions:" + posts.size());
            logger.debug("Upcoming auctions sent to frontend for pageNr " + posts.size() +" : "+ posts);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch(Exception e){

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.resolve(500));
    }

    @GetMapping("all")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    public @ResponseBody
    ResponseEntity<List<AuctionPostDTO>> getAllAuctions(@RequestParam(required = false) Integer pageSize,
                                                        @RequestParam(required = false) Integer pageNr) {
        logger.info("All auctions requested for page size "+pageSize +" and age number requested" + pageNr);
        List<AuctionPostDTO> posts = auctionService.getUpcomingAuctions(pageSize,pageNr);
        logger.info("Size of payload for all auctions: " + posts.size());
        logger.debug("All auctions sent to frontend for pageNr "+posts.size()+" : "+ posts);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("recent")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    public List<AuctionPostDTO> getRecentAuctions(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer auctionsPerPage) {
        logger.info("Recent auctions requested for page size "+auctionsPerPage+"and age number requested " + pageNumber);
        return auctionService.getRecentAuctions(pageNumber, auctionsPerPage);
    }
}
