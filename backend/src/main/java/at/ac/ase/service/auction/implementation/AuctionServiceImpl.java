package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.AuctionCreationDTO;
import at.ac.ase.entities.*;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.util.exception.ObjectNotFoundException;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.ase.service.auction.AuctionService;

@Service
public class AuctionServiceImpl{
//
//    @Autowired
//    private AuctionRepository auctionRepository;
//
//    @Autowired
//    private AuctionHouseService auctionHouseService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Override
//    public Optional<AuctionPost> getAuctionPost(Long id) {
//        return auctionRepository.findById(id);
//    }
//
//    @Override
//    public List<AuctionPost> getAllAuctions() {
//        return auctionRepository.findAll();
//    }
//
//    @Override
//    public AuctionPost createAuction(AuctionPost auctionPost) {
//        return auctionRepository.save(auctionPost);
//    }
//
//    @Override
//    public AuctionPost toAuctionPostEntity(User user, AuctionCreationDTO auctionPostDTO) {
//        AuctionPost auctionPost = modelMapper.map(auctionPostDTO, AuctionPost.class);
//        if (auctionPostDTO.getId() != null) {
//            auctionPost = auctionRepository
//                .findById(auctionPost.getId()).orElseThrow(ObjectNotFoundException::new);
//        } else {
//            auctionPost.setStatus(Status.UPCOMING);
//        }
//        auctionPost.setName(auctionPostDTO.getName());
//        auctionPost.setCategory(auctionPostDTO.getCategory());
//        auctionPost.setStartTime(auctionPostDTO.getStartTime());
//        auctionPost.setEndTime(auctionPostDTO.getEndTime());
//        auctionPost.setMinPrice(auctionPostDTO.getMinPrice());
//        auctionPost.setDescription(auctionPostDTO.getDescription());
//        auctionPost.setCreator(user);
//        auctionPost.setImage(Base64.getDecoder().decode(auctionPostDTO.getImage()));
//        auctionPost.setAddress(new Address(auctionPostDTO.getCountry(), auctionPostDTO.getCity(),
//                    auctionPostDTO.getAddress(), auctionPostDTO.getHouseNr()));
//        return auctionPost;
//    }
}
