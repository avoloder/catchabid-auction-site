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
     *
     * @param auctionsPerPage
     * @param pageNr
     * @return
     */
    List<AuctionPostSendDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr);

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
     * @param auctionPostDTO
     * @return
     */
    AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO);

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

    /**
     * Method which validates contact forms and persist it to the database
     * @param contactForm contact form to be validated and persisted
     * @return contact form with an id
     */
    ContactForm postContactForm(ContactForm contactForm);

    /**
     * Converts contactFormDTO to an entity
     * @param contactFormDTO DTO to be converted to an entity
     * @return ContactForm entity
     */
    ContactForm convertContactFormToDTO(ContactFormDTO contactFormDTO, User user);

    /**
     * Checks if the given {@link AuctionPost} is payable - it is finished AND has a bid
     *
     * @param auctionpost {@link AuctionPost} to be checked
     * @return true if the given {@link AuctionPost} object is payable
     */
    Boolean isAuctionPayable(AuctionPost auctionpost);

}
