package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.entities.Status;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import at.ac.ase.util.exception.ObjectNotFoundException;

import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.ase.service.auction.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionHouseService auctionHouseService;

    @Override
    public Optional<AuctionPost> getAuctionPost(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public AuctionPost createAuction(AuctionCreationDTO auctionPostDTO) {
        AuctionPost auctionPost;
        if (auctionPostDTO.getId() != null) {
            auctionPost = auctionRepository
                .findById(auctionPostDTO.getId()).orElseThrow(ObjectNotFoundException::new);
        } else {
            auctionPost = new AuctionPost();
        }
        auctionPost.setName(auctionPostDTO.getName());
        auctionPost.setCategory(auctionPostDTO.getCategory());
        auctionPost.setStartTime(auctionPostDTO.getStartTime());
        auctionPost.setEndTime(auctionPostDTO.getEndTime());
        auctionPost.setMinPrice(auctionPostDTO.getMinPrice());
        auctionPost.setDescription(auctionPostDTO.getDescription());
        auctionPost.setImage(Base64.getDecoder().decode(auctionPostDTO.getImage()));
        auctionPost.setStatus(Status.UPCOMING);
//        auctionPost.setCreator(
//            auctionHouseService
//                .getAuctionHouseById(auctionPostDTO.getCreatorId())
//                .orElseThrow(ObjectNotFoundException::new));

        return auctionRepository.save(auctionPost);
    }
}
