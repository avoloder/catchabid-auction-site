package at.ac.ase.service.user.rating.implementation;

import at.ac.ase.dto.AuctionPostSendDTO;
import at.ac.ase.dto.RatingDataDTO;
import at.ac.ase.entities.*;
import at.ac.ase.repository.user.AuctionHouseRepository;
import at.ac.ase.repository.user.RatingRepository;
import at.ac.ase.repository.user.UserRepository;
import at.ac.ase.service.user.rating.IRatingService;
import at.ac.ase.util.exceptions.UserAlreadyRatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;


    @Override
    public Double calculateRating(String email) {
        Set<Rating> ratingSet = null;
        RegularUser regularUser = userRepository.findByEmail(email);
        if (regularUser != null){
            ratingSet = regularUser.getRatings();
        } else {
            AuctionHouse auctionHouse = auctionHouseRepository.findByEmail(email);
            ratingSet = auctionHouse.getRatings();
        }
        Double sum = 0.0;
        for(Rating r: ratingSet){
            sum += r.getRating();
        }
        return sum / ratingSet.size();

    }

    @Override
    public void checkIfUserIsRatedAlready(AuctionPostSendDTO auctionPostSendDTO, User user){
        RatingPK ratingPK = new RatingPK(auctionPostSendDTO.getId(), user.getId());
        Optional<Rating> rating = ratingRepository.findById(ratingPK);
        if (rating.isPresent()) {
            throw new UserAlreadyRatedException();
        }
    }

    @Override
    public void setRating(RatingDataDTO ratingData, User user) {
        RatingPK ratingPK = new RatingPK(ratingData.getAuctionPost().getId(), user.getId());
        Rating rating = new Rating();
        rating.setId(ratingPK);
        RegularUser regularUser = userRepository.findByEmail(ratingData.getAuctionPost().getCreatorEmail());
        if (regularUser != null){
            rating.setUser(regularUser);
        } else {
            AuctionHouse auctionHouse = auctionHouseRepository.findByEmail(ratingData.getAuctionPost().getCreatorEmail());
            rating.setUser(auctionHouse);
        }
        rating.setRating(ratingData.getRatingValue());
        ratingRepository.save(rating);
    }
}
