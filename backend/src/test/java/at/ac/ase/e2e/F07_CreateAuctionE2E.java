package at.ac.ase.e2e;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.AuctionsListArea;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.CreateAuctionOverlay;
import at.ac.ase.e2e.pages.LoginOverlay;
import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class F07_CreateAuctionE2E extends BaseE2E {

    @AfterEach
    void cleanup() {
        cleanDatabase();
    }

    @Test
    void testCreateAuction() throws InterruptedException {

        insertTestData("F07_tc1.sql");

        navigateToCatchabidPage();

        CatchabidBasePage homePage = getCatchabidPage();
        homePage.clickLoginButton();

        LoginOverlay loginOverlay = getLoginOverlay();
        loginOverlay.loginUser("test@test.com", "Testtest!");


        Thread.sleep(2000);


        assertEquals("Testname", homePage.getDropdownToggleButton().getText());

        homePage.clickOnCreateAuctionButton();

        CreateAuctionOverlay createAuctionOverlay = getCreateAuctionOverlay();

        Calendar auctionStartDate = Calendar.getInstance();
        auctionStartDate.add(Calendar.MINUTE, 20);

        Calendar auctionEndDate = Calendar.getInstance();
        auctionEndDate.setTime(auctionStartDate.getTime());
        auctionEndDate.add(Calendar.MINUTE, 30);

        createAuctionOverlay.createAuction(
            "Test create auction",
            "electronics",
            auctionStartDate.getTime(),
            auctionEndDate.getTime(),
            "Austria",
            "Strassegasse",
            "1",
            "Vienna",
            100L,
            "Test item",
            System.getProperty("user.home").concat("/Downloads/offer_3.jpg")
        );

        Thread.sleep(2000);

        AuctionsListArea auctionsListArea = homePage.getAuctionsListArea();
        TestCase.assertEquals(1L, auctionsListArea.getUpcomingAuctions().size());
    }

}
