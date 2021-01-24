package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.LoginOverlay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class F09_ContactForm extends BaseE2E {

    @AfterEach
    void cleanup() {
        cleanDatabase();
    }

    @Test
    void testCreateContactForm() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();

        CatchabidBasePage homePage = getCatchabidPage();
        homePage.clickLoginButton();

        LoginOverlay loginOverlay = getLoginOverlay();
        loginOverlay.loginUser("user1@test.com", "test1234");

        Thread.sleep(2000);

        assertEquals("Homer Simpson", homePage.getDropdownToggleButton().getText());

        homePage.getDropdownToggleButton().click();
        homePage.clickMyProfileButton();
        getDriver().findElement(By.xpath("//a[contains(text(),'My Won Auctions')]")).click();

        getDriver().findElement(By.xpath("//button[contains(text(),'Fill contact form')]")).click();

        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        homePage.getToastMsg().click();
        homePage.getDropdownToggleButton().click();
        homePage.clickLogoutButton();

        homePage.clickLoginButton();
        loginOverlay.loginUser("auctionhouse1@test.com", "test1234");

        Thread.sleep(2000);

        assertEquals("Auction Master", homePage.getDropdownToggleButton().getText());

        homePage.getDropdownToggleButton().click();
        homePage.clickMyProfileButton();
        getDriver().findElement(By.xpath("//a[contains(text(),'My Auctions')]")).click();

        Thread.sleep(1000);

        assertTrue(getDriver().findElement(By.xpath("//button[contains(text(),'Check contact form')]")).isDisplayed());
    }
}
