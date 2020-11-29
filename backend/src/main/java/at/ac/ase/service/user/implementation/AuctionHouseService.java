package at.ac.ase.service.user.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.repository.user.AuctionHouseRepository;
import at.ac.ase.service.user.IAuctionHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionHouseService implements IAuctionHouseService {

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional
    public void changePassword(String email, String password) {
        String passwordHash = passwordEncoder.encode(password);
        auctionHouseRepository.changePassword(email, passwordHash);
    }
}
