package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.RatingDTO;
import at.ac.ase.dto.RatingDataDTO;
import at.ac.ase.entities.RatingPK;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.repository.user.RatingRepository;
import at.ac.ase.repository.user.UserRepository;
import at.ac.ase.service.auction.implementation.AuctionService;
import at.ac.ase.service.user.rating.implementation.RatingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RatingServiceTest extends BaseIntegrationTest {

    @Autowired
    AuctionService auctionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    @Before
    public void setup(){
        insertTestData("multiple-auctions.sql");
    }

    @After
    public void cleanup(){
        cleanDatabase();
    }

    @Test
    @Transactional
    public void testSetRating(){
        List<AuctionPostSendDTO> auctions = auctionService.getRecentAuctions(0, 0);
        RegularUser regularUser = userRepository.findByEmail("test@test.com");
        RatingDataDTO ratingDataDTO = new RatingDataDTO();
        ratingDataDTO.setAuctionPost(auctions.get(0));
        ratingDataDTO.setRatingValue(4);

        assertThat(ratingRepository.findAll().size(), is(0));
        ratingService.setRating(ratingDataDTO, regularUser);
        assertThat(ratingRepository.findAll().size(), is(1));
    }
}
