package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.service.auction.AuctionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class AuctionServiceTest extends BaseIntegrationTest {

    @Autowired
    AuctionService auctionService;

    @Test
    public void whenFindByName_thenReturnProduct() {
        assertNotNull(auctionService);
    }
}

