package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.CategoriesPickerOverlay;
import at.ac.ase.e2e.pages.LoginOverlay;
import at.ac.ase.e2e.pages.RegisterOverlay;
import at.ac.ase.entities.Address;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.repository.auction.AuctionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class F07_SignUpRegularUser extends BaseE2E {

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
    void testRegisterRegularUser() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();

        Thread.sleep(1000);

        Assert.assertNotNull(catchabidBasePage.getLoginButton());
        Assert.assertNotNull(catchabidBasePage.getRegisterButton());

        catchabidBasePage.clickRegisterButton();

        RegularUser user = createUser();

        RegisterOverlay registerOverlay = getRegisterOverlay();

        Thread.sleep(2000);

        Assertions.assertEquals("Register", registerOverlay.getOverlayTitle());

        registerOverlay.insertFirstName(user.getFirstName());
        registerOverlay.insertLastName(user.getLastName());
        registerOverlay.insertEmail(user.getEmail());
        registerOverlay.insertPassword("test12345");
        registerOverlay.insertRepeatPassword("test12345");
        registerOverlay.insertCountry(user.getAddress().getCountry());
        registerOverlay.insertCity(user.getAddress().getCity());
        registerOverlay.insertStreet(user.getAddress().getStreet());
        registerOverlay.insertHouseNumber(user.getAddress().getHouseNr());
        registerOverlay.insertAreaCode(1080);
        registerOverlay.insertPhoneNumber(user.getPhoneNr());

        registerOverlay.clickAgreeWithTermsAndConditions();

        registerOverlay.clickNextButton();

        Thread.sleep(2000);

        CategoriesPickerOverlay categoriesPickerOverlay = getCategoriesPickerOverlay();
        Assertions.assertEquals("Select your preferences (optional)", categoriesPickerOverlay.getOverlayTitle());

        List<String> categories = categoriesPickerOverlay.getDisplayedCategories();

        assertNotNull(categories);
        assertThat(categories.size(), is(10));
        assertTrue(categories.contains("JEWELRY"));
        assertTrue(categories.contains("ELECTRONICS"));
        assertTrue(categories.contains("ART"));
        assertTrue(categories.contains("CARS"));
        assertTrue(categories.contains("ANTIQUES"));
        assertTrue(categories.contains("TRAVEL"));
        assertTrue(categories.contains("FURNITURE"));
        assertTrue(categories.contains("MUSIC"));
        assertTrue(categories.contains("SPORT"));
        assertTrue(categories.contains("OTHER"));

        categoriesPickerOverlay.clickCategory("ART");
        categoriesPickerOverlay.clickCategory("FURNITURE");

        categoriesPickerOverlay.clickRegisterButton();

        Thread.sleep(2000);

        LoginOverlay loginOverlay = getLoginOverlay();

        Assertions.assertEquals("Sign in", loginOverlay.getOverlayTitle());

    }

    @Test
    void testLoginUser() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();

        Thread.sleep(1000);

        Assertions.assertNotNull(catchabidBasePage.getLoginButton());
        Assertions.assertNotNull(catchabidBasePage.getRegisterButton());

        catchabidBasePage.clickLoginButton();

        LoginOverlay loginOverlay = getLoginOverlay();

        Assertions.assertEquals("Sign in", loginOverlay.getOverlayTitle());

        loginOverlay.insertEmail("user1@test.com");
        loginOverlay.insertPassword("test1234");
        String username = "Homer Simpson";

        loginOverlay.clickLoginButton();

        Thread.sleep(3000);

        Assertions.assertNotNull(catchabidBasePage.getUsername(username));
    }

    private RegularUser createUser(){
        RegularUser regularUser = new RegularUser();
        regularUser.setEmail("maggiesimpson@gmail.com");
        regularUser.setFirstName("Maggie");
        regularUser.setLastName("Simpson");

        Address address = new Address();
        address.setCountry("USA");
        address.setCity("Springfield");
        address.setStreet("Evergreen Terrace");
        address.setHouseNr(742);

        regularUser.setAddress(address);
        regularUser.setPhoneNr("+43111000111");
        return regularUser;

    }
}
