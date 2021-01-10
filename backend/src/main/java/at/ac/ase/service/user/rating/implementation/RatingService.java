package at.ac.ase.service.user.rating.implementation;

import at.ac.ase.dto.RatingDataDTO;
import at.ac.ase.entities.*;
import at.ac.ase.repository.user.AuctionHouseRepository;
import at.ac.ase.repository.user.RatingRepository;
import at.ac.ase.repository.user.UserRepository;
import at.ac.ase.service.user.rating.IRatingService;
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
    public void setRating(RatingDataDTO ratingData, User user) {
        RatingPK ratingPK = new RatingPK(ratingData.getAuctionPost().getId(), user.getId());
        Rating rating = new Rating();
        rating.setId(ratingPK);
        RegularUser regularUser = userRepository.getOne(ratingData.getAuctionPost().getCreatorId());
        rating.setUser(regularUser);
        rating.setRating(ratingData.getRatingValue());
        ratingRepository.save(rating);
    }
}
