package at.ac.ase.service.auction;


import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionQueryDTO;
import at.ac.ase.dto.ContactFormDTO;
import at.ac.ase.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAuctionService {

    /**
     * Returns recent pagable auction posts ordered by start_time descending
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @return
     */
    List<AuctionPostSendDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage);

    /**
     * Returns recent pagable auction posts ordered by start_time descending based on user preferences
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @param usePreferences show auctions with user preferences or not
     * @param userEmail of the user
     * @return
     */
    List<AuctionPostSendDTO> getRecentAuctionsForUser(Integer pageNr, Integer auctionsPerPage,String userEmail,boolean usePreferences);

    /**
     *
     * @param auctionsPerPage
     * @param pageNr
     * @return
     */
    List<AuctionPostSendDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr);

    /**
     * Find auctions by a query where
     * @param query
     * @return found auctions
     */
    List<AuctionPostSendDTO> searchAuctions(AuctionQueryDTO query);

    /**
     *
     * @return
     */
    List<AuctionPost> getAllAuctions();

    /**
     *
     * @param auctionPost
     * @return
     */
    AuctionPost saveAuction(AuctionPost auctionPost);

    /**
     *
     * @param id
     * @return
     */
    Optional<AuctionPost> getAuctionPost(Long id);

    /**
     *
     * @param user
     * @param auctionPostDTO
     * @return
     */
    AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO);

    /**
     * Method which translates auction post send DTO to auction post entity
     * @param auctionPostDTO - auction post send DTO received from frontend
     * @return - tranlated auction post entity
     */
    AuctionPost toAuctionPostEntity( AuctionPostSendDTO auctionPostDTO);

    /**
     * Method which retrieves all auction post's categories
     * @return list of categories
     */
    Category[] getCategories();

    /**
     * Method which retrieves all countries where auctions exist
     * @return list of categories
     */
    List<String> getCountriesWhereAuctionsExist();

    ContactForm postContactForm(ContactForm contactForm);

    /**
     *
     * @param contactFormDTO
     * @param user
     * @return
     */
    ContactForm convertContactFormToDTO(ContactFormDTO contactFormDTO, User user);

    /**
     * Checks if the given {@link AuctionPost} is payable - it is finished AND has a bid
     *
     * @param auctionpost {@link AuctionPost} to be checked
     * @return true if the given {@link AuctionPost} object is payable
     */
    boolean isAuctionPayable(AuctionPost auctionpost);

    /**
     * Method which retrieves all won {@link AuctionPost}
     *
     * @param user
     * @return
     */
    List<AuctionPost> getAllWonAuctionPostsForUser(User user);

    /**
     * Method which subscribes user to specific auction post
     * @param auctionPost - auction post that the user wants to subscribe to
     * @param user - logged in user
     * @return - modified auction post to which the user subscribed to
     */
    AuctionPost subscribeToAuction(AuctionPost auctionPost, User user);

    /**
     * Method which unsubscribes user to specific auction post
     * @param auctionPost - auction post that the user wants to unsubscribe from
     * @param user - logged in user
     * @return - modified auction post to which the user unsubscribed from
     */
    AuctionPost unsubscribeFromAuction(AuctionPost auctionPost, User user);

    List<AuctionPostSendDTO> getMyAuctions(User user);
    Set<AuctionPostSendDTO> getMySubscriptions(RegularUser user);

    /**
     * Method which schedules notification job for recent and upcoming auctions
     * @param auctionPost - auction post for which the job is creeated
     */
    public void scheduleNotificationJob(AuctionPost auctionPost);

}
