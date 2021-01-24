package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.AuctionDetailsOverlay;
import at.ac.ase.e2e.pages.AuctionsListArea;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.LoginOverlay;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.repository.auction.AuctionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class F19_AuctionDetailView extends BaseE2E {

    @Autowired
    AuctionRepository auctionRepository;

    @AfterEach
    void testApplicationContext() {
        cleanDatabase();
    }

    @Test
    void testDbAccess() {
        insertTestData("F19_tc1.sql");
        assertEquals(16L, auctionRepository.findAll().size());
        cleanDatabase();
    }

    @Test
    void testShowAuctionDetailView() throws InterruptedException {
        insertTestData("F19_tc1.sql");

        navigateToCatchabidPage();
        loginUser();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();
        AuctionsListArea auctionsListArea = catchabidBasePage.getAuctionsListArea();

        Thread.sleep(3000);

        List<WebElement> recentAuctions = auctionsListArea.getRecentAuctions();

        Assertions.assertEquals(10, recentAuctions.size());

        auctionsListArea.clickShowDetails(recentAuctions.get(3));

        Thread.sleep(3000);

        AuctionDetailsOverlay auctionDetailsOverlay = auctionsListArea.getAuctionDetailsOverlay();

        Assertions.assertEquals("Cosy Couch", auctionDetailsOverlay.getOverlayTitle());
        Assertions.assertEquals("FURNITURE", auctionDetailsOverlay.getAuctionCategory());
        Assertions.assertEquals("Auction Master", auctionDetailsOverlay.getCreatorName());
        Assertions.assertEquals("Addr 44, Graz Austria", auctionDetailsOverlay.getAuctionAddress());
        Assertions.assertEquals("EUR 40", auctionDetailsOverlay.getAuctionMinPrice());

        auctionDetailsOverlay.exitDetailView();

    }

    private void loginUser() throws InterruptedException {
        CatchabidBasePage catchabidBasePage = getCatchabidPage();

        Thread.sleep(1000);

        catchabidBasePage.clickLoginButton();

        LoginOverlay loginOverlay = getLoginOverlay();

        loginOverlay.insertEmail("user1@test.com");
        loginOverlay.insertPassword("test1234");

        loginOverlay.clickLoginButton();
    }
}
