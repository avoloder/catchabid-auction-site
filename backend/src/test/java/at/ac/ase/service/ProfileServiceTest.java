package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.translator.AuctionHouseDtoTranslator;
import at.ac.ase.dto.translator.UserDtoTranslator;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.Category;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.bid.IBidService;
import at.ac.ase.service.statistics.IStatisticsService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import at.ac.ase.util.exceptions.EmptyObjectException;
import at.ac.ase.util.exceptions.UserNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ProfileServiceTest extends BaseIntegrationTest {

    @Autowired
    IAuctionService auctionService;
    @Autowired
    IAuctionHouseService auctionHouseService;
    @Autowired
    IRegularUserService regularUserService;
    @Autowired
    IStatisticsService statisticsService;
    @Autowired
    IBidService bidService;
    @Autowired
    UserDtoTranslator translator;
    @Autowired
    AuctionHouseDtoTranslator auctionHouseDtoTranslator;

    @After
    public void cleanup() {
        cleanDatabase();
    }
    @Before
    public void setup(){
        insertTestData("F32_tc1.sql");
    }

    @Test
    public void testUpdateUser(){
        RegularUser user = getRegularUserById(1l);
        String oldMail=user.getEmail();
        String oldNumber=user.getPhoneNr();
        Assert.assertEquals(oldMail,user.getEmail());
        Assert.assertEquals(oldNumber,user.getPhoneNr());
        user.setPhoneNr("+438895895589");
        user.setEmail("myTestMail@test.com");
        regularUserService.updateUser(oldMail,translator.toRegularUserDTO(user));
        RegularUser regularUserAfter = getRegularUserById(1l);
        Assert.assertNotEquals(oldMail,regularUserAfter.getEmail());
        Assert.assertNotEquals(oldNumber,regularUserAfter.getPhoneNr());
        RegularUser userEmpty =regularUserService.getUserByEmail(oldMail);
        Assert.assertNull(userEmpty);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateUserNotExisting(){
        RegularUser user = getRegularUserById(1l);
        user.setEmail("invalid@mail.com");
        regularUserService.updateUser(user.getEmail(),translator.toRegularUserDTO(user));
    }

    @Test
    public void testUpdateUserNoChanges(){
        RegularUser user = getRegularUserById(1l);
        regularUserService.updateUser(user.getEmail(),translator.toRegularUserDTO(user));
        RegularUser regularUserAfter = getRegularUserById(1l);
        user.equals(regularUserAfter);
        Assert.assertEquals(user,regularUserAfter);
    }

    @Test(expected = EmptyObjectException.class)
    public void testUpdateUserInvalidData(){
        RegularUser user = getRegularUserById(1l);
        regularUserService.updateUser(null,translator.toRegularUserDTO(user));
    }

    @Test
    public void testUpdateAuctionHouse(){
        AuctionHouse user = getAuctionHouseById(2l);
        String oldMail=user.getEmail();
        String oldNumber=user.getPhoneNr();
        Assert.assertEquals(oldMail,user.getEmail());
        Assert.assertEquals(oldNumber,user.getPhoneNr());
        user.setPhoneNr("+438895895589");
        user.setEmail("myTestMail@test.com");
        auctionHouseService.updateHouse(oldMail,auctionHouseDtoTranslator.toAuctionHouseDTO(user));
        AuctionHouse regularUserAfter = getAuctionHouseById(2l);
        Assert.assertNotEquals(oldMail,regularUserAfter.getEmail());
        Assert.assertNotEquals(oldNumber,regularUserAfter.getPhoneNr());
        AuctionHouse userEmpty =auctionHouseService.getAuctionHouseByEmail(oldMail);
        Assert.assertNull(userEmpty);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateAuctionHouseNotExisting(){
        AuctionHouse user = getAuctionHouseById(2l);
        auctionHouseService.updateHouse("oldMail@mail.com",auctionHouseDtoTranslator.toAuctionHouseDTO(user));
    }

    @Test
    public void testUpdateAuctionHouseNoChanges(){
        AuctionHouse user = getAuctionHouseById(2l);
        auctionHouseService.updateHouse(user.getEmail(),auctionHouseDtoTranslator.toAuctionHouseDTO(user));
        AuctionHouse houseAfter = getAuctionHouseById(2l);
        Assert.assertEquals(houseAfter,user);
    }

    @Test(expected = EmptyObjectException.class)
    public void testUpdateAuctionHouseInvalidData(){
        AuctionHouse user = getAuctionHouseById(2l);
        auctionHouseService.updateHouse(null,auctionHouseDtoTranslator.toAuctionHouseDTO(user));
    }




    @Test
    public void getRegularUserAuctions(){
       List<AuctionPostSendDTO> myAuctions= auctionService.getMyAuctions(getRegularUserById(6l));
       Assert.assertEquals(1,myAuctions.size());
       AuctionPostSendDTO post = myAuctions.get(0);
        Assert.assertEquals(java.util.Optional.of(6L).get(),post.getCreatorId());
        Assert.assertEquals("testRegular6@test.com",post.getCreatorEmail());
        Assert.assertEquals(Category.ELECTRONICS.name(),post.getCategory());
        Assert.assertEquals("Item1",post.getAuctionName()  );
        Assert.assertEquals("as sda sdasaddas as ddas as das as das",post.getDescription());
        Assert.assertEquals("Austria",post.getCountry());
        Assert.assertEquals("Vienna",post.getCity());
        Assert.assertEquals("street",post.getAddress());
        Assert.assertEquals(1,post.getHouseNr().intValue());
        Assert.assertEquals(java.util.Optional.of(Double.parseDouble("3.0")).get(),post.getMinPrice());

    }

    @Test
    public void getRegularUserAuctionsNoAuctions(){
        List<AuctionPostSendDTO> myAuctions= auctionService.getMyAuctions(getRegularUserById(1l));
        Assert.assertEquals(0,myAuctions.size());
    }

    @Test
    public void getAuctionHouseAuctions(){
        List<AuctionPostSendDTO> myAuctions= auctionService.getMyAuctions(getAuctionHouseById(2l));
        Assert.assertEquals(1,myAuctions.size());
    }

    @Test
    public void getAuctionHouseAuctionsNoAuctions(){
        List<AuctionPostSendDTO> myAuctions= auctionService.getMyAuctions(getAuctionHouseById(7l));
        Assert.assertEquals(0,myAuctions.size());
    }

    @Test
    public void getRegularUserSubscriptionsNoSubscriptions(){
        Set<AuctionPostSendDTO> subs = auctionService.getMySubscriptions(getRegularUserById(1l));
        Assert.assertEquals(0,subs.size());

    }

    @Test
    public void getRegularUserWinsNoWins(){
        Set<AuctionPostSendDTO> wins = auctionService.getMySubscriptions(getRegularUserById(6l));
        Assert.assertEquals(0,wins.size());
    }

    @Test
    public void getMyBids(){
        List<AuctionPostSendDTO> bids = bidService.getMyBids(getRegularUserById(1l));
        Assert.assertEquals(6,bids.size());
    }

    @Test
    public void getMyBidsNoBids(){
        List<AuctionPostSendDTO> bids = bidService.getMyBids(getRegularUserById(5l));
        Assert.assertEquals(0,bids.size());
    }

    private RegularUser getRegularUserById(long id){
        RegularUser user = regularUserService.getUserById(id).get();
        if (user==null){
            Assert.fail("Test user is missing ");
        }
        return user;
    }

    private AuctionHouse getAuctionHouseById(long id){
        AuctionHouse user = auctionHouseService.getAuctionHouseById(id).get();
        if (user==null){
            Assert.fail("Test auction house is missing ");
        }
        return user;
    }

}
