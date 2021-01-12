package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionQueryDTO;
import at.ac.ase.dto.ContactFormDTO;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.ContactForm;
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

    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @GetMapping("upcoming")
    public @ResponseBody
    ResponseEntity<List<AuctionPostSendDTO>> getUpcomingAuctions(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) boolean usePreferences) {
        logger.info("Upcoming auctions requested for page size " + pageSize + " and page number requested " + pageNumber);
        if ("null".equals(userEmail)) {
            List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNumber);
            logger.info("Size of payload for upcoming auctions:" + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctionsForUser(pageSize, pageNumber, userEmail,usePreferences);
            logger.info("Size of payload for upcoming auctions:" + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    @GetMapping("all")
    public @ResponseBody
    ResponseEntity<List<AuctionPostSendDTO>> getAllAuctions(@RequestParam(required = false) Integer pageSize,
                                                            @RequestParam(required = false) Integer pageNr) {
        logger.info("All auctions requested for page size " + pageSize + " and page number requested" + pageNr);
        List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctions(pageSize, pageNr);
        logger.info("Size of payload for all auctions: " + posts.size());
        logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("recent")
    public List<AuctionPostSendDTO> getRecentAuctions(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer auctionsPerPage,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) boolean usePreferences) {

        logger.info("Recent auctions requested for page size " + auctionsPerPage + "and page number requested " + pageNumber);
        if ("null".equals(userEmail)) {
            return auctionService.getRecentAuctions(pageNumber, auctionsPerPage);
        } else {
            List<AuctionPostSendDTO> posts = auctionService.getRecentAuctionsForUser(pageNumber, auctionsPerPage, userEmail,usePreferences);
            logger.info("Size of payload of recent auctions for user with the email " + userEmail + " is " + posts.size());
            logger.debug("Ids of Auctions in payload: " + posts.stream().map(AuctionPostSendDTO::getId).collect(Collectors.toList()));
            return posts;
        }
    }

    @GetMapping
    public ResponseEntity<List<AuctionPostSendDTO>> searchAuctions(AuctionQueryDTO query) {
        return ResponseEntity.ok(auctionService.searchAuctions(query));
    }

    @GetMapping("countriesWhereAuctionsExist")
    public ResponseEntity<List<String>> getCountriesWhereAuctionsExist() {
        return ResponseEntity.ok(auctionService.getCountriesWhereAuctionsExist());
    }

    @PostMapping("/save")
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

    @GetMapping("/getCategories")
    public ResponseEntity getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(this.auctionService.getCategories());
    }

    @PostMapping(value = "/postContactForm")
    public ResponseEntity<Object> postContactForm(
            @RequestBody ContactFormDTO contactFormDTO,
            @CurrentSecurityContext(expression = "authentication.principal") User user) {

        ContactForm contactForm = auctionService.convertContactFormToDTO(contactFormDTO, user);

        auctionService.postContactForm(contactForm);

        return ResponseEntity.ok(auctionService.postContactForm(contactForm));
    }

    @PostMapping(value = "/subscribe")
    public ResponseEntity<Object> subscribeToAuction(
            @RequestBody @Valid AuctionPostSendDTO auctionPostDTO,
            @CurrentSecurityContext(expression = "authentication.principal") User user) {

        AuctionPost auctionPost = auctionService.toAuctionPostEntity(user, auctionPostDTO);
        return ResponseEntity.ok(auctionService.subscribeToAuction(auctionPost, user));
    }
    @GetMapping("/won")
    public ResponseEntity<List<AuctionPostSendDTO>> getWonAuctionsForUser(
        @CurrentSecurityContext(expression = "authentication.principal") User user) {
        return ResponseEntity.ok(
            auctionDtoTranslator.toDtoList(
                auctionService.getAllWonAuctionPostsForUser(user)));
    }

    @PostMapping(value = "/unsubscribe")
    public ResponseEntity<Object> unsubscribeFromAuction(
            @RequestBody @Valid AuctionPostSendDTO auctionPostDTO,
            @CurrentSecurityContext(expression = "authentication.principal") User user) {

        AuctionPost auctionPost = auctionService.toAuctionPostEntity(user, auctionPostDTO);

        return ResponseEntity.ok(auctionService.unsubscribeFromAuction(auctionPost, user));
    }
}
