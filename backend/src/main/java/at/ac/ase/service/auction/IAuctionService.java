package at.ac.ase.service.auction;


import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionQueryDTO;
import at.ac.ase.dto.ContactFormDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Category;
import at.ac.ase.entities.ContactForm;
import at.ac.ase.entities.User;

import java.util.List;
import java.util.Optional;

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
     * Get upcomming auctions ordered by start-time ascending
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @return
     */
    List<AuctionPostSendDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);

    /**
     * Get upcomming auctions ordered by start-time ascending, based on user preferences
     * @param auctionsPerPage page size (default=50 if invalid parameter)
     * @param pageNr page number starting at 0. (default=0 if invalid parameter)
     * @param userEmail email of user whose preferences we should follow
     * @param usePreferences show auctions with user preferences or not
     * @return
     */
    List<AuctionPostSendDTO> getUpcomingAuctionsForUser(Integer auctionsPerPage, Integer pageNr,String userEmail,boolean usePreferences);

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
    AuctionPost createAuction(AuctionPost auctionPost);

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
     * Translates auction post send DTO to auction post entity
     * @param user - logged in user
     * @param auctionPostSendDTO - auction post send DTO received from frontend
     * @return - tranlated auction post entity
     */
    AuctionPost toAuctionPostEntity(User user, AuctionPostSendDTO auctionPostDTO);

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

    AuctionPost subscribeToAuction(AuctionPost auctionPost, User user);

    AuctionPost unsubscribeFromAuction(AuctionPost auctionPost, User user);

}
