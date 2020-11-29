package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.service.auth.implementation.RegisterService;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class RegisterServiceTest extends BaseIntegrationTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    UserService userService;

    @Autowired
    AuctionHouseService auctionHouseService;

    @Autowired
    PasswordEncoder encoder;

    @After
    public void cleanup() {
        cleanDatabase();
    }

    @Test
    public void test_registerUser() {
        assertEquals(0, userService.getAllUsers().size());
        RegularUser user = new RegularUser();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("test@gmail.com");
        user.setPasswordHash("password");
        Address address = new Address();
        address.setCity("Vienna");
        address.setCountry("Austria");
        address.setHouseNr(3);
        address.setStreet("Karlsplatz");
        user.setAddress(address);

        registerService.registerUser(user);
        assertEquals(1, userService.getAllUsers().size());

    }

    @Test
    public void test_registerHouse() {
        assertEquals(0, auctionHouseService.getAllHouses().size());
        AuctionHouse house = new AuctionHouse();
        house.setName("Auction House");
        house.setEmail("house@gmail.com");
        house.setPasswordHash("testPassword");
        Address address = new Address();
        address.setCity("Vienna");
        address.setCountry("Austria");
        address.setHouseNr(3);
        address.setStreet("Karlsplatz");
        house.setAddress(address);

        registerService.registerHouse(house);
        assertEquals(1, auctionHouseService.getAllHouses().size());
    }
}
