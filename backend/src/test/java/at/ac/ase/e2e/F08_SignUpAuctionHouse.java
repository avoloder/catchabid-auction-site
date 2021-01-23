package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.e2e.pages.LoginOverlay;
import at.ac.ase.e2e.pages.RegisterOverlay;
import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.repository.auction.AuctionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertEquals;

public class F08_SignUpAuctionHouse extends BaseE2E {

    @Autowired
    private AuctionRepository auctionRepository;

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
    void testRegisterAuctionHouse() throws InterruptedException {
        insertTestData("initial-testdata.sql");

        navigateToCatchabidPage();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();

        Thread.sleep(1000);

        Assert.assertNotNull(catchabidBasePage.getLoginButton());
        Assert.assertNotNull(catchabidBasePage.getRegisterButton());

        catchabidBasePage.clickRegisterButton();

        RegisterOverlay registerOverlay = getRegisterOverlay();

        registerOverlay.clickAuctionHouseTab();

        Thread.sleep(5000);

        Assertions.assertEquals("Register", registerOverlay.getOverlayTitle1());

        AuctionHouse auctionHouse = createAuctionHouse();

        registerOverlay.insertName(auctionHouse.getName());
        registerOverlay.insertTid(auctionHouse.getTid());
        registerOverlay.insertBusinessEmail(auctionHouse.getEmail());
        registerOverlay.insertPasswordAuction("test1234");
        registerOverlay.insertRepeatPasswordAuction("test1234");
        registerOverlay.insertCountryAuction(auctionHouse.getAddress().getCountry());
        registerOverlay.insertAreaCodeAuction(057);
        registerOverlay.insertCityAuction(auctionHouse.getAddress().getCity());
        registerOverlay.insertStreetAuction(auctionHouse.getAddress().getStreet());
        registerOverlay.insertHouseNumberAuction(auctionHouse.getAddress().getHouseNr());
        registerOverlay.clickAgreeWithTermsAndConditionsAuction();
        registerOverlay.insertPhoneNumberAuction(auctionHouse.getPhoneNr());
        registerOverlay.clickRegisterButton();
        Thread.sleep(2000);
        LoginOverlay loginOverlay = getLoginOverlay();
        Assertions.assertEquals("Sign in", loginOverlay.getOverlayTitle());
    }

    private AuctionHouse createAuctionHouse(){
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.setEmail("maggiesimpson@gmail.com");
        auctionHouse.setName("AH Test");
        auctionHouse.setTid("0123456789");

        Address address = new Address();
        address.setCountry("USA");
        address.setCity("Springfield");
        address.setStreet("Evergreen Terrace");
        address.setHouseNr(742);

        auctionHouse.setAddress(address);
        auctionHouse.setPhoneNr("+43111000111");
        return auctionHouse;

    }
}
