package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.service.users.AuctionHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionHouseServiceImpl implements AuctionHouseService {

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Override
    public Optional<AuctionHouse> getAuctionHouseById(Long id) {
        return auctionHouseRepository.findById(id);
    }

    @Override
    public List<AuctionHouse> getAllHouses(){
        return auctionHouseRepository.findAll();
    }

    @Override
    public AuctionHouse getAuctionHouseByEmail(String email) {
        return auctionHouseRepository.findByEmail(email);
    }
}
