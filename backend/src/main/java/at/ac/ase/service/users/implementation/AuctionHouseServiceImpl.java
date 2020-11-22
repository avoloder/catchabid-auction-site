package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.service.users.AuctionHouseService;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionHouseServiceImpl implements AuctionHouseService {

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Override
    public Optional<AuctionHouse> getAuctionHouseById(Long id) {
        return auctionHouseRepository.findById(id);
    }

    @Override
    public AuctionHouse getAuctionHouseByEmail(Map<String, String> userData) {
        String email = userData.get("email");
        return auctionHouseRepository.findByEmail(email);
    }

    @Override
    public AuctionHouse getAuctionHouseByEmail(String email) {
        return auctionHouseRepository.findByEmail(email);
    }
}
