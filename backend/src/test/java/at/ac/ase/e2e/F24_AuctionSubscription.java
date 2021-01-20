package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.AuctionsListArea;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.LoginOverlay;
import at.ac.ase.repository.auction.AuctionRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class F24_AuctionSubscription extends BaseE2E {

    @Autowired
    AuctionRepository auctionRepository;

    @AfterEach
    void testApplicationContext() {
        cleanDatabase();
    }

    @Test
    void testDbAccess() {
        insertTestData("initial-testdata.sql");
        assertEquals(16L, auctionRepository.findAll().size());
        cleanDatabase();
    }

    @Test
    void testSubscribeToAuction() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();
        loginUser();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();
        AuctionsListArea auctionsListArea = catchabidBasePage.getAuctionsListArea();

        Thread.sleep(3000);

        List<WebElement> upcomingAuctions = auctionsListArea.getUpcomingAuctions();

        Assertions.assertEquals(3, upcomingAuctions.size());

        auctionsListArea.clickSubscribeToAuction(upcomingAuctions.get(0));
    }

    @Test
    void testUnsubscribeFromAuction() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();
        loginUser();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();
        AuctionsListArea auctionsListArea = catchabidBasePage.getAuctionsListArea();

        Thread.sleep(3000);

        List<WebElement> upcomingAuctions = auctionsListArea.getUpcomingAuctions();

        Assertions.assertEquals(3, upcomingAuctions.size());

        auctionsListArea.clickSubscribeToAuction(upcomingAuctions.get(0));

        Thread.sleep(5000);

        auctionsListArea.clickUnsubscribeFromAuction(upcomingAuctions.get(0));
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
