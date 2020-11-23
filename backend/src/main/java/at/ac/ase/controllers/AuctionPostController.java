package at.ac.ase.controllers;

import at.ac.ase.dto.AuctionDto;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auction")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class AuctionPostController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @GetMapping("recent")
    public List<AuctionDto> getRecentAuctions(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer auctionsPerPage)
    {
        return auctionDtoTranslator.toDtoList(auctionService.getRecentAuctions(pageNumber, auctionsPerPage));
    }
}
