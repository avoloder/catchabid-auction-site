package at.ac.ase.service.auction.implementation;

import at.ac.ase.dto.BidDTO;
import at.ac.ase.dto.translator.BidDtoTranslator;
import at.ac.ase.entities.Bid;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.auction.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.ase.service.auction.BidService;

@Service
public class BidServiceImplementation implements BidService {

    @Autowired
    private BidDtoTranslator bidDtoTranslator;

    @Autowired
    private BidRepository bidRepository;


    @Override
    public Bid toBid(BidDTO bidDTO, User user) {
        Bid bid = bidDtoTranslator.toBid(bidDTO);
        bid.setUser((RegularUser) user);
        return bid;
    }

    @Override
    public Bid placeBid(Bid bid) {
        return bidRepository.save(bid);

    }
}
