package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.service.auth.implementation.RegisterService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;


public class RegisterServiceTest extends BaseIntegrationTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    IRegularUserService IRegularUserService;

    @Autowired
    IAuctionHouseService IAuctionHouseService;

    @Autowired
    PasswordEncoder encoder;

    @After
    public void cleanup() {
        cleanDatabase();
    }

    @Test
    public void test_registerUser() {
        assertEquals(0, IRegularUserService.getAllUsers().size());
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
        assertEquals(1, IRegularUserService.getAllUsers().size());

    }

    @Test
    public void test_registerHouse() {
        assertEquals(0, IAuctionHouseService.getAllHouses().size());
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
        assertEquals(1, IAuctionHouseService.getAllHouses().size());
    }

    @Test
    public void test_userAlreadyRegisteredThrowsException(){
        RegularUser user = new RegularUser();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPasswordHash("testPassword");
        user.setEmail("test@gmail.com");
        registerService.registerUser(user);
        assertEquals(1, IRegularUserService.getAllUsers().size());
        registerService.registerUser(user);
    }
}
