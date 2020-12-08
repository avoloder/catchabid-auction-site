package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.entities.*;
import at.ac.ase.repository.auction.AuctionRepository;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import at.ac.ase.util.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuctionService implements IAuctionService {
    private static final Logger logger = LoggerFactory.getLogger(AuctionService.class);

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private IRegularUserService regularUserService;

    @Autowired
    private IAuctionHouseService auctionHouseService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @Override
    public Optional<AuctionPost> getAuctionPost(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public AuctionPost createAuction(AuctionPost auctionPost) {
        return auctionRepository.save(auctionPost);
    }

    @Override
    public AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO) {
        AuctionPost auctionPost = modelMapper.map(auctionPostDTO, AuctionPost.class);
        if (auctionPostDTO.getId() != null) {
            auctionPost = auctionRepository
                    .findById(auctionPost.getId()).orElseThrow(ObjectNotFoundException::new);
        } else {
            auctionPost.setStatus(Status.UPCOMING);
        }
        auctionPost.setName(auctionPostDTO.getName());
        auctionPost.setCategory(auctionPostDTO.getCategory());
        auctionPost.setStartTime(auctionPostDTO.getStartTime());
        auctionPost.setEndTime(auctionPostDTO.getEndTime());
        auctionPost.setMinPrice(auctionPostDTO.getMinPrice());
        auctionPost.setDescription(auctionPostDTO.getDescription());
        auctionPost.setCreator(user);
        auctionPost.setImage(Base64.getDecoder().decode(auctionPostDTO.getImage()));
        auctionPost.setAddress(new Address(auctionPostDTO.getCountry(), auctionPostDTO.getCity(),
                auctionPostDTO.getAddress(), auctionPostDTO.getHouseNr()));
        return auctionPost;
    }

    @Override
    public Category[] getCategories() {
        Category[] categories = Category.values();
        return categories;
    }

    @Override
    public List<AuctionPostSendDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage) {
        logger.info("Fetching recent auctions without preferences, for pageNumber " + pageNr + ", and page size " + auctionsPerPage);
        List<AuctionPost> recentAuctions = auctionRepository.findAllByStartTimeLessThanAndEndTimeGreaterThan(
                LocalDateTime.now(), LocalDateTime.now(), getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").descending()));
        logger.debug("Fetched " + recentAuctions.size() + " auctions from database.");
        return auctionDtoTranslator.toDtoList(recentAuctions);
    }

    @Override
    public List<AuctionPostSendDTO> getRecentAuctionsForUser(Integer pageNr, Integer auctionsPerPage, Long userId) {
        Set<Category> preferences = getPreferences(userId);
        if (preferences == null || preferences.isEmpty()) {
            logger.info("No preferences found, continue with fetching recent auctions without preferences");
            return getRecentAuctions(pageNr, auctionsPerPage);
        } else {
            logger.info("Fetching recent auctions with preferences: " + preferences.toString() + "pageNumber " + pageNr + ", and page size " + auctionsPerPage);
            List<AuctionPost> recentAuctions = auctionRepository.findAllByStartTimeLessThanAndEndTimeGreaterThanAndCategoryIn(
                    LocalDateTime.now(), LocalDateTime.now(), preferences, getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").descending()));
            logger.debug("Fetched " + recentAuctions.size() + " auctions from database.");
            return auctionDtoTranslator.toDtoList(recentAuctions);
        }
    }

    @Override
    public List<AuctionPostSendDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr) {
        logger.info("Fetching upcoming auctions without preferences, for pageNumber " + pageNr + ", and page size " + auctionsPerPage);
        List<AuctionPost> upcomingAuctions =auctionRepository.findAllByStartTimeGreaterThan(LocalDateTime.now(),
                getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending()));
        logger.debug("Fetched " + upcomingAuctions.size() + " auctions from database.");
        return convertAuctionsToDTO(upcomingAuctions);
    }

    @Override
    public List<AuctionPostSendDTO> getUpcomingAuctionsForUser(Integer auctionsPerPage, Integer pageNr, Long userId) {
        Set<Category> preferences = getPreferences(userId);
        if (preferences == null || preferences.isEmpty()) {
            logger.info("No preferences found, continue with retrieving upcoming auctions without preferences");
            return getUpcomingAuctions(pageNr, auctionsPerPage);
        } else {
            logger.info("Retrieving upcoming auctions with preferences: " + preferences.toString() + " for pageNumber " + pageNr + ", and page size " + auctionsPerPage);
            List<AuctionPost> upcomingAuctions = auctionRepository.findAllByStartTimeGreaterThanAndCategoryIn(LocalDateTime.now(), preferences,
                    getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending()));
            logger.debug("Fetched " + upcomingAuctions.size() + " auctions from database.");
            return convertAuctionsToDTO(upcomingAuctions);

        }
    }

    @Override
    public List<AuctionPost> getAllAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public List<AuctionPostSendDTO> getAllAuctions(Integer auctionsPerPage, Integer pageNr) {
        Page<AuctionPost> result = auctionRepository.findAll(getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending()));
        return convertAuctionsToDTO(result.getContent());
    }

    private List<AuctionPostSendDTO> convertAuctionsToDTO(Collection<AuctionPost> auctions) {
        List<AuctionPostSendDTO> auctionPostSendDTOS = new ArrayList<>();
        auctions.forEach((x) -> auctionPostSendDTOS.add(auctionDtoTranslator.toSendDto(x)));
        return auctionPostSendDTOS;
    }

    private Pageable getPageForFutureAuctions(Integer auctionsPerPage, Integer pageNr, Sort sortMethod) {
        if (pageNr == null || pageNr < 0) {
            pageNr = 0;
        }
        if (auctionsPerPage == null || auctionsPerPage < 1) {
            auctionsPerPage = 50;
        }
        return PageRequest.of(pageNr, auctionsPerPage, sortMethod);
    }

    private Set<Category> getPreferences(Long id) {
        Optional<RegularUser> regularUser = regularUserService.getUserById(id);
        if (regularUser.isPresent()) {
            return regularUser.get().getPreferences();
        }

        return null;
    }
}
