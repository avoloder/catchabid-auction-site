package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.AuctionQueryDTO;
import at.ac.ase.dto.ContactFormDTO;
import at.ac.ase.dto.translator.AuctionDtoTranslator;
import at.ac.ase.entities.*;
import at.ac.ase.repository.auction.AuctionPostQuery;
import at.ac.ase.repository.auction.AuctionRepository;
import at.ac.ase.repository.user.UserRepository;
import at.ac.ase.repository.auction.ContactFormRepository;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.user.IAuctionHouseService;
import at.ac.ase.service.user.IRegularUserService;
import com.lowagie.text.DocumentException;
import org.apache.commons.io.FileUtils;
import at.ac.ase.util.exceptions.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private ContactFormRepository contactFormRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuctionDtoTranslator auctionDtoTranslator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    IRegularUserService userService;


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    @Override
    public Optional<AuctionPost> getAuctionPost(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public AuctionPost saveAuction(AuctionPost auctionPost) {
        return auctionRepository.save(auctionPost);
    }

    @Override
    @Transactional
    public AuctionPostSendDTO cancelAuction(User user, Long auctionPostId) {
        AuctionPost auction = auctionRepository.findById(auctionPostId)
                .orElseThrow(ObjectNotFoundException::new);

        if(!auction.getCreator().getId().equals(user.getId())) {
            throw new AuthorizationException();
        }
        if(auction.isUpcoming()) {
            auction.setStatus(Status.CANCELLED);
        }
        else {
            throw new AuctionCancellationException();
        }
        return auctionDtoTranslator.toSendDto(auction, true);
    }

    @Override
    public AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
    public AuctionPost toAuctionPostEntity( AuctionPostSendDTO auctionPostDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AuctionPost auctionPost = modelMapper.map(auctionPostDTO, AuctionPost.class);
        if (auctionPostDTO.getId() != null) {
            auctionPost = auctionRepository
                    .findById(auctionPost.getId()).orElseThrow(ObjectNotFoundException::new);
        } else {
            auctionPost.setStatus(Status.UPCOMING);
        }
        auctionPost.setName(auctionPostDTO.getAuctionName());
        auctionPost.setCategory(Category.valueOf(auctionPostDTO.getCategory()));
        auctionPost.setStartTime(auctionPostDTO.getStartTime());
        auctionPost.setEndTime(auctionPostDTO.getEndTime());
        auctionPost.setMinPrice(auctionPostDTO.getMinPrice());
        auctionPost.setDescription(auctionPostDTO.getDescription());
        Optional<AuctionHouse> auctionHouse = auctionHouseService.getAuctionHouseById(auctionPostDTO.getCreatorId());
        if (auctionHouse.isPresent()){
            auctionPost.setCreator(auctionHouse.get());
        }else{
            Optional<RegularUser> regularUser = regularUserService.getUserById(auctionPostDTO.getCreatorId());
            if (regularUser.isPresent()){
                auctionPost.setCreator(regularUser.get());
            }else{
                if (auctionPost.getCreator()==null){
                    //ERROR Object incomplete
                }
            }
        }
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
    public List<String> getCountriesWhereAuctionsExist() {
        return auctionRepository.getAllCountriesWhereAuctionsExist();
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
    public List<AuctionPostSendDTO> getRecentAuctionsForUser(Integer pageNr, Integer auctionsPerPage, String userEmail, boolean usePreferences) {
        List<Category> preferences = getPreferences(userEmail, usePreferences);
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
        List<AuctionPost> upcomingAuctions = auctionRepository.findAllByStartTimeGreaterThan(LocalDateTime.now(),
                getPageForFutureAuctions(auctionsPerPage, pageNr, Sort.by("startTime").ascending()));
        logger.debug("Fetched " + upcomingAuctions.size() + " auctions from database.");
        return convertAuctionsToDTO(upcomingAuctions);
    }

    @Override
    public List<AuctionPostSendDTO> getUpcomingAuctionsForUser(Integer auctionsPerPage, Integer pageNr, String userEmail, boolean usePreferences) {
        List<Category> preferences = getPreferences(userEmail, usePreferences);
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

    @Override
    public List<AuctionPostSendDTO> searchAuctions(AuctionQueryDTO query) {
        AuctionPostQuery entityQuery = auctionDtoTranslator.toEntity(query);
        if (entityQuery.isEmptySearch()) {
            entityQuery.setCategories(getPreferences(entityQuery.getUserEmail(), entityQuery.isUseUserPreferences()));
        }
        List<AuctionPost> foundAuctions = auctionRepository.query(entityQuery);
        logger.info("Size of auctions: " + foundAuctions.size());
        return auctionDtoTranslator.toDtoList(foundAuctions);
    }

    private List<AuctionPostSendDTO> convertAuctionsToDTO(Collection<AuctionPost> auctions) {
        List<AuctionPostSendDTO> auctionPostSendDTOS = new ArrayList<>();
        auctions.forEach((x) -> auctionPostSendDTOS.add(auctionDtoTranslator.toSendDto(x, true)));
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

    private List<Category> getPreferences(String userEmail, boolean usePreferences) {
        RegularUser regularUser = regularUserService.getUserByEmail(userEmail);
        if (regularUser != null) {
            if (usePreferences) {
                return regularUser.getPreferences().stream().collect(Collectors.toList());
            } else {
                return Arrays.stream(getCategories()).filter(e -> !regularUser.getPreferences().contains(e)).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
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

    @Override
    public boolean isAuctionPayable(AuctionPost auctionpost) {
        return Objects.nonNull(auctionpost.getHighestBid()) &&
            auctionpost.getEndTime().isBefore(LocalDateTime.now());
    }

    @Override
    public List<AuctionPost> getAllWonAuctionPostsForUser(User user) {
        return auctionRepository.findAllByHighestBidUserIdAndEndTimeLessThan(
            user.getId(), LocalDateTime.now());
    }
    @Override
    public AuctionPost subscribeToAuction(AuctionPost auctionPost, User user) {
        if(auctionPost.getCreator().getId().equals(user.getId())) {
            throw new WrongSubscriberException();
        }
        if(auctionPost.getStatus().equals(Status.CANCELLED)){
            throw new AuctionCancelledException();
        }
        Set<RegularUser> subscriptions = auctionPost.getSubscriptions();
        subscriptions.add((RegularUser) user);
        auctionPost.setSubscriptions(subscriptions);
        return auctionRepository.save(auctionPost);
    }

    @Override
    public AuctionPost unsubscribeFromAuction(AuctionPost auctionPost, User user) {
        Set<RegularUser> subscriptions = auctionPost.getSubscriptions();
        subscriptions.removeIf(subscribedUser -> subscribedUser.getId().equals(user.getId()));
        auctionPost.setSubscriptions(subscriptions);
        return auctionRepository.save(auctionPost);
    }

    public List<AuctionPostSendDTO> getMyAuctions(User user){
        List<AuctionPost> retrieved = auctionRepository.findALlByCreatorId(user.getId());
        logger.info("Retrieved these posts " + retrieved );
        return auctionDtoTranslator.toDtoList(retrieved);
    }

    public Set<AuctionPostSendDTO> getMySubscriptions(RegularUser user){
        Set<AuctionPost> retrieved = userService.getUserByEmail(user.getEmail()).getSubscriptions();
                logger.info("subs"+retrieved);
        return auctionDtoTranslator.toDtoSet(retrieved);
    }

    @Override
    public AuctionPost sendConfirmation(AuctionPost auctionPost, User user) throws IOException, DocumentException {
       generatePdfFromHtml(parseThymeleafTemplate(auctionPost));
       sendConfirmationEmail(new RegularUser());
        return null;
    }

    private String parseThymeleafTemplate(AuctionPost auctionPost) throws IOException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        //byte[] fileContent = FileUtils.readFileToByteArray(new File(getClass().getClassLoader().getResource("catchabid-logo.png").getFile()));
        //String encodedString = Base64.getEncoder().encodeToString(fileContent);


        Context context = new Context();
        context.setVariable("auction", auctionPost);
        context.setVariable("price", 12.22);
        //context.setVariable("image", "data:image/png;base64, " + encodedString);

        return templateEngine.process("confirmation", context);
    }

    private void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf2.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private void sendConfirmationEmail(RegularUser user){
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply.catchabid@gmail.com");
            helper.setTo("mensur_b_7@hotmail.com");
            helper.setSubject("Confirmation about won auction");
            helper.setText("Dear " + //user.getFirstName() +
                    " " + //user.getLastName() +
                    ", <br/> congratulations on winning an auction. " +
                    "You can find the official confirmation about the won auction in the attachment. <br/>", true);

            FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home") + File.separator + "thymeleaf2.pdf"));
            helper.addAttachment("confirmation.pdf", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailNotSentException();
        }
    }



}
