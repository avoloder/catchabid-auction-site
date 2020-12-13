package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.ContactFormDTO;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.entities.*;
import at.ac.ase.repository.auction.AuctionRepository;
import at.ac.ase.repository.auction.ContactFormRepository;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.util.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private IAuctionHouseService auctionHouseService;

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


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
        Category [] categories = Category.values();
        return categories;
    }

    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @Override
    public List<AuctionPostSendDTO> getRecentAuctions(Integer pageNr, Integer auctionsPerPage) {
        List<AuctionPost> recentAuctions = auctionRepository.findAllByStartTimeLessThanAndEndTimeGreaterThan(
                LocalDateTime.now(), LocalDateTime.now(), getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").descending()));
        return auctionDtoTranslator.toDtoList(recentAuctions);
    }

    @Override
    public List<AuctionPostSendDTO> getUpcomingAuctions(Integer auctionsPerPage, Integer pageNr) {
        return convertAuctionsToDTO(auctionRepository.findAllByStartTimeGreaterThan(LocalDateTime.now(),
                getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending())));
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

    public ContactForm postContactForm(ContactForm contactForm) {
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        if (violations.isEmpty()) {
            return contactFormRepository.save(contactForm);
        } else {
            for (ConstraintViolation<ContactForm> violation : violations) {
                throw new ValidationException(violation.getMessage());
            }
            return null;
        }
    }

    public ContactForm convertContactFormToDTO(ContactFormDTO contactFormDTO, User user) {
        ContactForm contactForm = modelMapper.map(contactFormDTO, ContactForm.class);

        contactForm.setAddress(new Address(contactFormDTO.getCountry(), contactFormDTO.getCity(), contactFormDTO.getStreet(), contactFormDTO.getHouseNr()));
        contactForm.setUser(user);
        auctionRepository.findById(contactFormDTO.getAuctionPostId()).ifPresent(contactForm::setAuctionPost);

        return contactForm;
    }

}
