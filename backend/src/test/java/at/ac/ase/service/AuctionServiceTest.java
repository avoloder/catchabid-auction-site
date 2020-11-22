package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.service.auction.AuctionService;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AuctionServiceTest extends BaseIntegrationTest {

    @Autowired
    AuctionService auctionService;

    @After
    public void cleanup() {
        cleanDatabase();
    }

    @Test
    public void testServiceNotNull() {
        assertNotNull(auctionService);
    }

    @Test
    @Transactional
    public void testGetRecentAuctions() {
        List<AuctionPost> auctions;
        insertTestData("multiple-auctions.sql");

        auctions= auctionService.getRecentAuctions(0, 5);

        assertThat(auctions.size(), is(5));
        assertThat(auctions.get(0).getId(), is(10L));
        assertThat(auctions.get(1).getId(), is(11L));
        assertThat(auctions.get(2).getId(), is(9L));
        assertThat(auctions.get(3).getId(), is(8L));
        assertThat(auctions.get(4).getId(), is(7L));

        auctions= auctionService.getRecentAuctions(1, 5);

        assertThat(auctions.size(), is(5));
        assertThat(auctions.get(0).getId(), is(6L));
        assertThat(auctions.get(1).getId(), is(5L));
        assertThat(auctions.get(2).getId(), is(4L));
        assertThat(auctions.get(3).getId(), is(3L));
        assertThat(auctions.get(4).getId(), is(2L));

        auctions= auctionService.getRecentAuctions(2, 5);

        assertThat(auctions.size(), is(1));
        assertThat(auctions.get(0).getId(), is(1L));
    }

    @Test
    @Transactional
    public void testGetRecentAuctionsWithInvalidParam() {
        insertTestData("multiple-auctions.sql");

        List<AuctionPost> auctions = auctionService.getRecentAuctions(0, 0);
        assertThat(auctions.size(), is(11));
        assertThat(auctions.get(0).getId(), is(10L));
        assertThat(auctions.get(10).getId(), is(1L));

        auctions = auctionService.getRecentAuctions(null, null);
        assertThat(auctions.size(), is(11));
        assertThat(auctions.get(0).getId(), is(10L));
        assertThat(auctions.get(10).getId(), is(1L));

        auctions = auctionService.getRecentAuctions(-1, 10);
        assertThat(auctions.size(), is(11));
        assertThat(auctions.get(0).getId(), is(10L));
        assertThat(auctions.get(10).getId(), is(1L));
    }
}

