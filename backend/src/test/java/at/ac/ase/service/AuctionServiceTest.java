package at.ac.ase.service;

import at.ac.ase.basetest.BaseIntegrationTest;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.entities.*;
import at.ac.ase.repository.auction.AuctionRepository;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.util.exceptions.AuctionCancellationException;
import at.ac.ase.util.exceptions.AuctionCancelledException;
import at.ac.ase.util.exceptions.AuthorizationException;
import at.ac.ase.util.exceptions.WrongSubscriberException;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class AuctionServiceTest extends BaseIntegrationTest {

    @Autowired
    IAuctionService auctionService;

    @Autowired
    IAuctionHouseService auctionHouseService;

    @Autowired
    AuctionRepository auctionRepository;

    @After
    public void cleanup() {
        cleanDatabase();
    }

    @Test
    public void testServiceNotNull() {
        assertNotNull(auctionService);
    }

    @Test
    @Transactional
    public void testGetRecentAuctions() {
        List<AuctionPostSendDTO> auctions;
        insertTestData("multiple-auctions.sql");

        auctions = auctionService.getRecentAuctions(0, 5);

        assertThat(auctions.size(), is(5));
        assertThat(auctions.get(0).getId(), is(1L));
        assertThat(auctions.get(1).getId(), is(2L));
        assertThat(auctions.get(2).getId(), is(3L));
        assertThat(auctions.get(3).getId(), is(4L));
        assertThat(auctions.get(4).getId(), is(5L));

        auctions = auctionService.getRecentAuctions(1, 5);

        assertThat(auctions.size(), is(5));
        assertThat(auctions.get(0).getId(), is(6L));
        assertThat(auctions.get(1).getId(), is(7L));
        assertThat(auctions.get(2).getId(), is(8L));
        assertThat(auctions.get(3).getId(), is(9L));
        assertThat(auctions.get(4).getId(), is(10L));

        auctions = auctionService.getRecentAuctions(2, 5);

        assertThat(auctions.size(), is(1));
        assertThat(auctions.get(0).getId(), is(11L));
    }

    @Test
    @Transactional
    public void testGetRecentAuctionsWithInvalidParam() {
        insertTestData("multiple-auctions.sql");

        List<AuctionPostSendDTO> auctions = auctionService.getRecentAuctions(0, 0);
        assertThat(auctions.size(), is(11));
        assertThat(auctions.get(10).getId(), is(1L));
        assertThat(auctions.get(0).getId(), is(11L));

        auctions = auctionService.getRecentAuctions(null, null);
        assertThat(auctions.size(), is(11));
        assertThat(auctions.get(0).getId(), is(11L));
        assertThat(auctions.get(10).getId(), is(1L));

        auctions = auctionService.getRecentAuctions(-1, 10);
        assertThat(auctions.size(), is(10));
        assertThat(auctions.get(0).getId(), is(11L));
        assertThat(auctions.get(9).getId(), is(2L));
    }

    @Test
    public void testCreateAuction() {
        insertTestData("auctions.sql");

        AuctionPost auctionPost = new AuctionPost();

        AuctionHouse auctionHouse = auctionHouseService.getAuctionHouseByEmail("test@test.com");

        List<AuctionPost> auctionPosts = auctionService.getAllAuctions();

        assertNotNull(auctionHouse);
        assertThat(auctionPosts.size(), is(1));

        auctionPost.setName("TestAuction");
        auctionPost.setDescription("TestDesc");
        auctionPost.setMinPrice(10.0);
        auctionPost.setStartTime(LocalDateTime.now());
        auctionPost.setEndTime(LocalDateTime.now().plusHours(2));
        auctionPost.setCreator(auctionHouse);
        auctionPost.setCategory(Category.CARS);
        auctionPost.setStatus(Status.ACTIVE);

        auctionService.saveAuction(auctionPost);

        auctionPosts = auctionService.getAllAuctions();
        assertThat(auctionPosts.size(), is(2));
    }

    @Test
    @Transactional
    public void testRecentAuctionsForUser(){
        insertTestData("auctions-filter-preferences.sql");
        List<AuctionPostSendDTO> posts = auctionService.getRecentAuctionsForUser(0,10,"testRegular@test.com",true);
        assertEquals(1,posts.size());
        assertEquals("Desktop PC - Intel i7, AMD RX 580 8GB",posts.get(0).getDescription());

        posts = auctionService.getRecentAuctionsForUser(0,10,"testRegular@test.com",false);
        assertEquals(2,posts.size());
        assertEquals("Bob Marley Tickets",posts.get(0).getDescription());
        assertEquals("Ticket to Paradise CD",posts.get(1).getDescription());

    }

    @Test
    @Transactional
    public void testUpcomingAuctionsForUser(){
        insertTestData("auctions-filter-preferences.sql");
        List<AuctionPostSendDTO> posts = auctionService.getUpcomingAuctionsForUser(10,0,"testRegular@test.com",true);
        assertEquals(1,posts.size());
        assertEquals("Desktop PC - Intel i7, AMD RX 580 8GB",posts.get(0).getDescription());

        posts = auctionService.getUpcomingAuctionsForUser(10,0,"testRegular@test.com",false);
        assertEquals(2,posts.size());
        assertEquals("Bob Marley Tickets",posts.get(0).getDescription());
        assertEquals("Ticket to Paradise CD",posts.get(1).getDescription());

    }

    @Test
    @Transactional
    public void testGetUpcomingAuctions() {
        insertTestData("auctions.sql");

        List<AuctionPostSendDTO> upcoming = auctionService.getUpcomingAuctions(0, 0);
        assertEquals(0, upcoming.size());

        AuctionPost auctionPost =auctionService.getAllAuctions().get(0);
        auctionPost.setStartTime(LocalDateTime.now().plusHours(2));
        auctionPost.setEndTime(LocalDateTime.now().plusHours(12));
        auctionPost.setStatus(Status.UPCOMING);

        auctionService.saveAuction(auctionPost);

        upcoming = auctionService.getUpcomingAuctions(0, 0);
        assertEquals(1, upcoming.size());
        assertEquals(auctionPost.getId(), upcoming.get(0).getId());
    }

    @Test
    @Transactional
    public void testSubscribeToAuction(){
        insertTestData("multiple-auctions.sql");

        List<AuctionPost> auctionPosts = auctionService.getAllAuctions();
        assertThat(auctionPosts.size(), is(11));

        AuctionPost auctionPost = auctionPosts.get(0);
        assertThat(auctionPost.getSubscriptions().size(), is(0));

        User user = new RegularUser();
        user.setId(2L);
        user.setActive(true);
        user.setEmail("test@test.com");

        auctionService.subscribeToAuction(auctionPost, user);

        auctionPost = auctionService.getAllAuctions().get(0);

        assertThat(auctionPost.getSubscriptions().size(), is(1));

        Iterator iterator = auctionPost.getSubscriptions().iterator();
        RegularUser subscribedUser = (RegularUser)iterator.next();
        assertThat(subscribedUser.getId(), is(2L));
        assertTrue(subscribedUser.getActive());
        assertThat(subscribedUser.getEmail(), is("test@test.com"));
    }

    @Test
    @Transactional
    public void testUnsubscribeFromAuction(){
        insertTestData("multiple-auctions.sql");

        List<AuctionPost> auctionPosts = auctionService.getAllAuctions();
        assertThat(auctionPosts.size(), is(11));

        AuctionPost auctionPost = auctionPosts.get(0);
        assertThat(auctionPost.getSubscriptions().size(), is(0));

        User user = new RegularUser();
        user.setId(2L);
        user.setActive(true);
        user.setEmail("test@test.com");

        auctionService.subscribeToAuction(auctionPost, user);

        auctionPost = auctionService.getAllAuctions().get(0);

        assertThat(auctionPost.getSubscriptions().size(), is(1));

        auctionService.unsubscribeFromAuction(auctionPost, user);

        auctionPost = auctionService.getAllAuctions().get(0);

        assertThat(auctionPost.getSubscriptions().size(), is(0));
    }

    @Test(expected = WrongSubscriberException.class)
    @Transactional
    public void testSubscribeToAuctionWrongSubscriberError(){
        insertTestData("multiple-auctions.sql");

        List<AuctionPost> auctionPosts = auctionService.getAllAuctions();
        assertThat(auctionPosts.size(), is(11));

        AuctionPost auctionPost = auctionPosts.get(1);
        assertThat(auctionPost.getSubscriptions().size(), is(0));

        User user = new RegularUser();
        user.setId(2L);
        user.setActive(true);
        user.setEmail("test@test.com");

        auctionService.subscribeToAuction(auctionPost, user);
    }

    @Test(expected = AuctionCancelledException.class)
    @Transactional
    public void testSubscribeToAuctionAuctionCancelledError(){
        insertTestData("multiple-auctions.sql");

        List<AuctionPost> auctionPosts = auctionService.getAllAuctions();
        assertThat(auctionPosts.size(), is(11));

        AuctionPost auctionPost = auctionPosts.get(0);
        assertThat(auctionPost.getSubscriptions().size(), is(0));

        auctionPost.setStatus(Status.CANCELLED);
        auctionService.saveAuction(auctionPost);

        User user = new RegularUser();
        user.setId(2L);
        user.setActive(true);
        user.setEmail("test@test.com");

        auctionService.subscribeToAuction(auctionPost, user);
    }

    @Test(expected = AuthorizationException.class)
    public void testCancelAuctionAuthorizationError() {
        insertTestData("initial-testdata.sql");
        User user = new AuctionHouse();
        user.setId(99L);
        auctionService.cancelAuction(user, 100017L);
    }

    @Test(expected = AuctionCancellationException.class)
    public void testCancelAuctionCancellationError() {
        insertTestData("initial-testdata.sql");
        User user = new AuctionHouse();
        user.setId(2L);
        auctionService.cancelAuction(user, 100016L);
    }

    @Test
    public void testCancelAuctionSuccess() {
        insertTestData("initial-testdata.sql");
        User user = new AuctionHouse();
        user.setId(3L);

        AuctionPostSendDTO cancelledAuctionDto = auctionService.cancelAuction(user, 100017L);
        AuctionPost cancelledAuctionDb = auctionRepository.findById(100017L).orElse(null);

        assertNotNull(cancelledAuctionDto);
        assertNotNull(cancelledAuctionDb);
        assertEquals("CANCELLED", cancelledAuctionDto.getStatus());
        assertEquals(Status.CANCELLED, cancelledAuctionDb.getStatus());
    }

    @Test(expected = AuthorizationException.class)
    public void testPostContactFormFalseUserError() {
        insertTestData("initial-testdata.sql");

        User user = new AuctionHouse();
        user.setId(5L);

        auctionService.postContactForm(100010L, user, createContactForm());
    }

    @Test
    public void testPostContactFormSuccess() {
        insertTestData("initial-testdata.sql");

        User user = new AuctionHouse();
        user.setId(3L);

        AuctionPost auctionPost = auctionRepository.findById(100010L).orElse(null);

        assertNotNull(auctionPost);
        assertNull(auctionPost.getContactForm());

        AuctionPostSendDTO auctionPostSendDTO = auctionService.postContactForm(100010L, user, createContactForm());

        assertNotNull(auctionPostSendDTO.getContactFormSubmitted());
        assertTrue(auctionPostSendDTO.getContactFormSubmitted());

        auctionPost = auctionRepository.findById(100010L).orElse(null);

        assertNotNull(auctionPost);
        assertNotNull(auctionPost.getContactForm());
    }

    @Test
    public void testGetContactFormSuccess() {
        insertTestData("initial-testdata.sql");

        User user = new AuctionHouse();
        user.setId(3L);

        AuctionPost auctionPost = auctionRepository.findById(100010L).orElse(null);

        assertNotNull(auctionPost);
        assertNull(auctionPost.getContactForm());

        auctionService.postContactForm(100010L, user, createContactForm());

        User user1 = new AuctionHouse();
        user1.setId(2L);

        assertEquals(Long.valueOf(2), auctionService.getContactForm(100010L, user1).getId());
    }

    private ContactForm createContactForm() {
        ContactForm contactForm = new ContactForm();
        contactForm.setFirstName("Test First");
        contactForm.setLastName("Test Last");

        Address address = new Address();
        address.setStreet("Resselgasse");
        address.setHouseNr(1);
        address.setCity("Vienna");
        address.setCountry("Austria");

        contactForm.setAddress(address);
        contactForm.setPhoneNr("062749403274");
        contactForm.setEmail("test2@gmail.com");
        contactForm.setRemark("No Remark");

        return contactForm;
    }

    private AuctionPost createAuction(String email) {
        AuctionPost auctionPost = new AuctionPost();
        auctionPost.setEndTime(LocalDateTime.now());
        auctionPost.setStartTime(LocalDateTime.now().minusMinutes(55));
        auctionPost.setName("TestAuction");
        auctionPost.setDescription("TestDesc");
        auctionPost.setMinPrice(10.0);
        auctionPost.setCreator(auctionHouseService.getAuctionHouseByEmail(email));
        auctionPost.setCategory(Category.CARS);
        auctionPost.setStatus(Status.ACTIVE);
        auctionPost.setImage(getImageBytes());

        Address address = new Address();
        address.setStreet("Resselgasse");
        address.setHouseNr(1);
        address.setCity("Vienna");
        address.setCountry("Austria");
        auctionPost.setAddress(address);

        return auctionPost;
    }

    public byte[] getImageBytes() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("catchabid-logo.png")) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[10];
    }
}

