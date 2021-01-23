package at.ac.ase.e2e;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.LoginOverlay;
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
        homePage.clickOnLoginButton();

        LoginOverlay loginOverlay = getLoginOverlayPage();
        loginOverlay.loginUser("test@test.com", "Testtest!");

        assertEquals("Testname", homePage.getDropdownToggleTitle());

        

    }

}
